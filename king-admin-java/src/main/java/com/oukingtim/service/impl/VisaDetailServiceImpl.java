package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.File;
import com.oukingtim.domain.RegionData;
import com.oukingtim.domain.VisaModel;
import com.oukingtim.mapper.VisaMapper;
import com.oukingtim.service.FileService;
import com.oukingtim.service.RegionDataService;
import com.oukingtim.service.VisaDetailService;
import com.oukingtim.util.BizException;
import com.oukingtim.web.vm.ResultVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <br>创建日期：2018/4/17
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class VisaDetailServiceImpl extends ServiceImpl<VisaMapper, VisaModel> implements VisaDetailService {

    private static Logger logger = LoggerFactory.getLogger(VisaDetailServiceImpl.class);

    @Autowired
    private RegionDataService regionDataService;

    @Autowired
    private FileService fileService;

    @Override
    public Page<VisaModel> selectPage(Page<VisaModel> page, Wrapper<VisaModel> wrapper) {
        super.selectPage(page, wrapper);
        if (page != null && !CollectionUtils.isEmpty(page.getRecords())) {
            for (VisaModel visaModel : page.getRecords()) {
                RegionData regionData = regionDataService.selectById(visaModel.getCountry());
                if (regionData != null) {
                    visaModel.setCountryName(regionData.getName());
                }
            }
        }
        /*
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(baseMapper.selectPage(page, wrapper));*/
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(VisaModel visaModel) {
        boolean flag = false;
        if (visaModel != null) {
            VisaModel model = new VisaModel();
            model.setCountry(visaModel.getCountry());
            List<VisaModel> visaModels = super.selectList(new EntityWrapper<>(model));
            if (!CollectionUtils.isEmpty(visaModels)) {
                logger.error("[国家id-{}]-签证已有", visaModel.getCountry());
                throw new BizException("该国签证信息已存在");
            }
            flag = super.insert(visaModel);
            if (!CollectionUtils.isEmpty(visaModel.getFileList())) {
                for (File file : visaModel.getFileList()) {
                    file.setDelete(0);
                    file.setCreateTime(new Date());
                    file.setUpdateTime(new Date());
                    file.setTypeId(visaModel.getId());
                }
                fileService.insertBatch(visaModel.getFileList());
            }
        }
        return flag;

    }

    @Override
    public boolean updateById(VisaModel visaModel) {
        boolean flag = false;
        String visaId = visaModel.getId();
        if (visaModel != null) {
            if (visaId == null) {
                return flag;
            }
            File file = new File();
            file.setTypeId(visaId);
            //删除visaid已有文件
            fileService.delete(new EntityWrapper<>(file));
            if (!CollectionUtils.isEmpty(visaModel.getFileList())) {
                for (File file1 : visaModel.getFileList()) {
                    file1.setDelete(0);
                    file1.setCreateTime(new Date());
                    file1.setUpdateTime(new Date());
                    file1.setTypeId(visaModel.getId());
                }
                fileService.insertBatch(visaModel.getFileList());
            }
            flag = super.updateById(visaModel);

        }
        return flag;
    }

    @Override
    public VisaModel selectById(Serializable id){
        VisaModel visaModelVO = super.selectById(id);
        if(visaModelVO != null){
            String visaId = visaModelVO.getId();
            File file = new File();
            file.setTypeId(visaId);
            List<File> fileList = fileService.selectList(new EntityWrapper<>(file));
            visaModelVO.setFileList(fileList);
        }
        return visaModelVO;
    }

}
