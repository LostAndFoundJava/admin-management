package com.oukingtim.web;

import com.oukingtim.domain.Category;
import com.oukingtim.domain.Exhibition;
import com.oukingtim.web.vm.ResultVM;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xufan on 2018/04/02.
 */
@RestController
@Api(description = "首页管理")
@RequestMapping("/mgr/home")
public class HomeConfController {

    private static Logger logger = LoggerFactory.getLogger(HomeConfController.class);

    @RequestMapping(value = "/configure" , method = RequestMethod.POST)
    public ResultVM homeConfig(Category category, Exhibition exhibition){


        return ResultVM.ok();
    }


}
