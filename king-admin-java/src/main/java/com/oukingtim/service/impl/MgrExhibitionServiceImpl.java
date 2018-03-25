package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.Exhibition;
import com.oukingtim.domain.ExhibitionDetail;
import com.oukingtim.domain.File;
import com.oukingtim.mapper.MgrExhibitionDetailMapper;
import com.oukingtim.mapper.MgrExhibitionMapper;
import com.oukingtim.mapper.MgrFileMapper;
import com.oukingtim.service.MgrExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;

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
                exhibitionDetail.setCreateTime(new Date());
                exhibitionDetail.setUpdateTime(new Date());
                mgrExhibitionDetailMapper.insert(exhibitionDetail);
                if (!CollectionUtils.isEmpty(exhibitionDetail.getFiles())) {
                    for (File file : exhibitionDetail.getFiles()) {
                        file.setCreateTime(new Date());
                        file.setUpdateTime(new Date());
                        mgrFileMapper.insert(file);
                    }
                }
            }
            return flag;
        }
        return flag;
    }

    @Override
    public Exhibition selectById(Integer id) {
        Exhibition exhibitionVO = super.selectById(id);
        if (exhibitionVO != null) {
            ExhibitionDetail detail = mgrExhibitionDetailMapper.selectById(id);
            if (detail != null) {
                if (!CollectionUtils.isEmpty(detail.getFiles())) {
                    detail.setFiles(detail.getFiles());
                }
                exhibitionVO.setExhibitionDetail(detail);
            }
        }
        return exhibitionVO;
    }

}
