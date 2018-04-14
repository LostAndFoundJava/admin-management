package com.oukingtim.service;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.RegionData;

import java.util.List;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public interface RegionDataService  extends IService<RegionData>{

    List<RegionData> getCountryList();

    List<RegionData> getCityList(Integer countryId);
}
