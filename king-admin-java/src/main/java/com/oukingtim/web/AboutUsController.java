package com.oukingtim.web;

import com.oukingtim.domain.AboutUsModel;
import com.oukingtim.domain.Category;
import com.oukingtim.service.AboutUsService;
import com.oukingtim.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>创建日期：2018/4/16
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@Api(description = "网站主页aboutus")
@RequestMapping("/mgr/aboutus/management")
public class AboutUsController extends MgrBaseController<AboutUsService, AboutUsModel> {

    @GetMapping("/getAboutUsInfo")
    public AboutUsModel getAboutUsInfo() {
        return service.getAboutUsInfo();
    }

}
