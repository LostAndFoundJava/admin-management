package com.oukingtim.web;

import com.oukingtim.domain.RegionData;
import com.oukingtim.service.RegionDataService;
import com.oukingtim.web.vm.ResultVM;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@Api(description = "地域管理")
@RequestMapping("/mgr/regionData/management")
public class RegionDataController {
    private static final Logger logger = LoggerFactory.getLogger(RegionDataController.class);

    @Autowired
    RegionDataService service;

    @GetMapping("/getContinentList")
    public ResultVM getContinentList() {
        List<RegionData> regionDataList = service.getContinentList();
        return ResultVM.ok(regionDataList);
    }

    @GetMapping("/getCountryList")
    public ResultVM getCountryList() {
        List<RegionData> regionDataList = service.getCountryList();
        return ResultVM.ok(regionDataList);
    }

    @GetMapping("/getCityList")
    public ResultVM getCityList(@RequestParam(required = false) Integer countryId) {
        List<RegionData> regionDataList = service.getCityList(countryId);
        return ResultVM.ok(regionDataList);
    }

    @GetMapping("/country")
    public ResultVM getCountryList(@RequestParam(required = false) Integer continentId) {
        List<RegionData> regionDataList = service.getCountryList(continentId);
        return ResultVM.ok(regionDataList);
    }

    @GetMapping("/regionData")
    public ResultVM getRegionData(@RequestParam(required = false) Integer pid){
        List<RegionData> regionDataList = service.getRegionDataList(pid);
        return ResultVM.ok(regionDataList);
    }
}
