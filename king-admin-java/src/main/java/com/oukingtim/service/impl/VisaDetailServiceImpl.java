package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.RegionData;
import com.oukingtim.domain.VisaModel;
import com.oukingtim.mapper.VisaMapper;
import com.oukingtim.service.RegionDataService;
import com.oukingtim.service.VisaDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * <br>创建日期：2018/4/17
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class VisaDetailServiceImpl extends ServiceImpl<VisaMapper,VisaModel>implements VisaDetailService{

    @Autowired
    private RegionDataService regionDataService;

    @Override
    public Page<VisaModel> selectPage(Page<VisaModel> page, Wrapper<VisaModel> wrapper) {
        super.selectPage(page,wrapper);
        if(page != null && !CollectionUtils.isEmpty(page.getRecords())) {
            for (VisaModel visaModel : page.getRecords()) {
                RegionData regionData = regionDataService.selectById(visaModel.getCountry());
                if(regionData != null){
                    visaModel.setCountryName(regionData.getName());
                }
            }
        }
        /*
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(baseMapper.selectPage(page, wrapper));*/
        return page;
    }
}
