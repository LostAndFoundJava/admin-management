package com.oukingtim.web;

import com.oukingtim.domain.AboutUsModel;
import com.oukingtim.domain.Category;
import com.oukingtim.service.AboutUsService;
import com.oukingtim.service.CategoryService;
import io.swagger.annotations.Api;
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
@RequestMapping("/api/mgr/aboutus")
public class AboutUsController extends MgrBaseController<AboutUsService, AboutUsModel> {
}
