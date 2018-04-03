package com.oukingtim.web;

import com.oukingtim.domain.HomePageHotConfig;
import com.oukingtim.service.HomePageService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xufan on 2018/04/02.
 */
@RestController
@Api(description = "首页管理")
@RequestMapping("/mgr/home")
public class HomeConfController extends MgrBaseController<HomePageService,HomePageHotConfig>{

    private static Logger logger = LoggerFactory.getLogger(HomeConfController.class);

}
