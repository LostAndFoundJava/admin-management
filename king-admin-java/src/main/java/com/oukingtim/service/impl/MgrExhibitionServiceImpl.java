package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.Exhibition;
import com.oukingtim.domain.ExhibitionDetail;
import com.oukingtim.domain.File;
import com.oukingtim.domain.SysUser;
import com.oukingtim.mapper.MgrExhibitionDetailMapper;
import com.oukingtim.mapper.MgrExhibitionMapper;
import com.oukingtim.mapper.MgrFileMapper;
import com.oukingtim.service.MgrExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xufan on 2018/03/19.
 */
@Service
public class MgrExhibitionServiceImpl extends ServiceImpl<MgrExhibitionMapper, Exhibition> implements MgrExhibitionService {

    @Autowired
    private MgrExhibitionDetailMapper mgrExhibitionDetailMapper;

    @Autowired
    private MgrFileMapper mgrFileMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(Exhibition exhibition) {
        boolean flag = false;
        if (exhibition != null) {
            flag = super.insert(exhibition);
            ExhibitionDetail exhibitionDetail = exhibition.getExhibitionDetail();
            if (exhibitionDetail != null) {
                exhibitionDetail.setExhibitionId(exhibition.getId());
                exhibitionDetail.setCreateTime(new Date());
                exhibitionDetail.setUpdateTime(new Date());
                mgrExhibitionDetailMapper.insert(exhibitionDetail);
                if (!CollectionUtils.isEmpty(exhibitionDetail.getFiles())) {
                    for (File file : exhibitionDetail.getFiles()) {
                        file.setTypeId(exhibitionDetail.getId());
                        file.setCreateTime(new Date());
                        file.setUpdateTime(new Date());
                        file.setDelete(0);
                        mgrFileMapper.insert(file);
                    }
                }
            }
            return flag;
        }
        return flag;
    }

    @Override
    public Exhibition selectById(Serializable id) {
        Exhibition exhibitionVO = super.selectById(id);
        String exhibitionId = exhibitionVO.getId();
        if (exhibitionVO != null) {
            List<ExhibitionDetail> detailList = mgrExhibitionDetailMapper.selectList(new EntityWrapper<>(new ExhibitionDetail(exhibitionId)));
            if(!CollectionUtils.isEmpty(detailList)){
                ExhibitionDetail detail = detailList.get(0);
                if (detail != null) {
                    String typeId =  detail.getId();
                    List<File> fileList = mgrFileMapper.selectList(new EntityWrapper<>(new File(typeId)));
                    if (!CollectionUtils.isEmpty(fileList)) {
                        detail.setFiles(fileList);
                    }
                    exhibitionVO.setExhibitionDetail(detail);
                }
            }

        }
        return exhibitionVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Exhibition exhibition){
        boolean flag = false;
        ExhibitionDetail entity = new ExhibitionDetail();
        //展会id
        String exhibitionId = exhibition.getId();
        if (exhibition != null) {
            //更新展会
            flag = super.updateById(exhibition);

            //获取上传的展会详情
            ExhibitionDetail exhibitionDetail = exhibition.getExhibitionDetail();
            if (exhibitionDetail != null) {
                entity.setExhibitionId(exhibitionId);
                //删除展会详情
                mgrExhibitionDetailMapper.delete(new EntityWrapper<>(entity));

                exhibitionDetail.setExhibitionId(exhibitionId);
                exhibitionDetail.setCreateTime(new Date());
                exhibitionDetail.setUpdateTime(new Date());
                //插入展会详情
                mgrExhibitionDetailMapper.insert(exhibitionDetail);
                if (!CollectionUtils.isEmpty(exhibitionDetail.getFiles())) {
                    File file = new File();
                    file.setTypeId(exhibitionDetail.getId());
                    //删除展会详情相关的文件(图片)
                    mgrFileMapper.delete(new EntityWrapper<>(file));

                    //更新展会详情文件
                    for (File fileEntity : exhibitionDetail.getFiles()) {
                        fileEntity.setTypeId(exhibitionDetail.getId());
                        fileEntity.setCreateTime(new Date());
                        fileEntity.setUpdateTime(new Date());
                        mgrFileMapper.insert(fileEntity);
                    }
                }
            }else{
                entity.setExhibitionId(exhibitionId);
                mgrExhibitionDetailMapper.delete(new EntityWrapper<>(entity));
            }
            return flag;
        }
        return flag;

    }
}
