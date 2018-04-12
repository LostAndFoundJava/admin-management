package com.oukingtim.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.Category;
import com.oukingtim.domain.Exhibition;

import java.util.List;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public interface CategoryService extends IService<Category> {




    /**
     * 添加行业
     *
     * @param category
     * @return
     */

    List<Category> insertCategory(Category category);

    /**
     * 删除行业（删除子行业)
     *
     * @param category
     * @return
     */

    boolean delectSubCategory(Category category);

    /**
     * 删除行业（删除父行业)
     *
     * @param category
     * @return
     */

    boolean delectParCategory(Category category);

    List<Category> getList(String homepageId);

}
