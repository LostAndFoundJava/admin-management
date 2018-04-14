package com.oukingtim.web;

import com.oukingtim.domain.Exhibition;
import com.oukingtim.domain.HomePageHotConfig;
import com.oukingtim.service.HomePageService;
import com.oukingtim.web.vm.ResultVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by xufan on 2018/04/02.
 */
@RestController
@Api(description = "首页管理")
@RequestMapping("/mgr/homepage/management")
public class HomeConfController extends MgrBaseController<HomePageService,HomePageHotConfig>{

    private static Logger logger = LoggerFactory.getLogger(HomeConfController.class);

    @ApiOperation(value = "获取展会列表", notes = "根据行业ids获取展会列表")
    @RequestMapping(value = "/exhibitions", method = RequestMethod.GET)
    public ResultVM getExhibitionsByHomepageId(@RequestParam(required = false) String homepageId) {
        List<Exhibition> exhibitions = service.getExhibitionsByHomepageId(homepageId);
        return ResultVM.ok(exhibitions);
    }
}
