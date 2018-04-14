package com.oukingtim.web;

import com.oukingtim.domain.RegionData;
import com.oukingtim.domain.SysRole;
import com.oukingtim.service.RegionDataService;
import com.oukingtim.web.vm.ResultVM;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api(value = "地域管理")
@RequestMapping("/mgr/regionData/management")
public class RegionDateController extends MgrBaseController<RegionDataService,RegionData>  {
    private static final Logger logger = LoggerFactory.getLogger(RegionDateController.class);

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


}
