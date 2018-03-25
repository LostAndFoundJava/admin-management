package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.Category;
import com.oukingtim.mapper.CategoryInfoMapper;
import com.oukingtim.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryInfoMapper, Category> implements CategoryService {









    @Override
    public List<Category> insertCategory(Category category) {
        return null;
    }

    /**
     * 删除行业（删除子行业)
     *
     * @param category
     * @return
     */

    @Override
    public boolean delectSubCategory(Category category) {

        return false;
    }

    /**
     * 删除行业（删除父行业)
     *
     * @param category
     * @return
     */
    @Override
    public boolean delectParCategory(Category category) {
        return false;
    }

}
