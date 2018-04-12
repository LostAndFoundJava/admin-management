package com.oukingtim.web.catagetory;

import com.oukingtim.domain.Category;
import com.oukingtim.domain.Exhibition;
import com.oukingtim.domain.SysRole;
import com.oukingtim.service.CategoryService;
import com.oukingtim.web.MgrBaseController;
import com.oukingtim.web.vm.ResultVM;
<<<<<<< HEAD
import io.swagger.annotations.ApiOperation;
=======
>>>>>>> bdaadb568b5c124bd820fdcd4b83afc8ce4ccfae
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import java.util.List;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@RequestMapping("/mgr/category/management")
public class CategoryManageController extends MgrBaseController<CategoryService, Category> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryManageController.class);

<<<<<<< HEAD
    /**
     * 获取行业集合
     */
    @GetMapping("/getList")
    public ResultVM getList(@RequestParam(required = false) String homepageId) {
        List<Category> categoryList = service.getList(homepageId);
        return ResultVM.ok(categoryList);
    }


=======
    @RequestMapping("/all")
    public ResultVM getAllCategoryName(){
        List<Category> list = service.getAllCategoryName();
        return ResultVM.ok(list);
    }
>>>>>>> bdaadb568b5c124bd820fdcd4b83afc8ce4ccfae
}
