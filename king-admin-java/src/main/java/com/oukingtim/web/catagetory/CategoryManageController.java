package com.oukingtim.web.catagetory;

import com.oukingtim.domain.Category;
import com.oukingtim.service.CategoryService;
import com.oukingtim.web.MgrBaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@RequestMapping("/mgr/category")
public class CategoryManageController extends MgrBaseController<CategoryService, Category> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryManageController.class);



    


}
