package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.RegionData;
import com.oukingtim.mapper.RegionDataMapper;
import com.oukingtim.service.RegionDataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class RegionDataServiceImpl extends ServiceImpl<RegionDataMapper, RegionData> implements RegionDataService{

    private static final Integer CONTINENT_LEVEL = 1;
    private static final Integer COUNTRY_LEVER = 2;

    @Override
    public List<RegionData> getCountryList() {
        List<RegionData> regionDataList = super.selectList(new EntityWrapper<>(new RegionData(COUNTRY_LEVER)));
        return regionDataList;
    }

    @Override
    public List<RegionData> getCityList(Integer countryId) {
        RegionData regionData = new RegionData();
        regionData.setPid(countryId);
        List<RegionData> regionDataList = super.selectList(new EntityWrapper<>(regionData));
        return regionDataList;
    }
}
