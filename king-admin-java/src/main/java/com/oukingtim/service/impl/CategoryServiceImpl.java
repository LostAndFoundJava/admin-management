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
    @Autowired
    private CategoryInfoMapper categoryInfoMapper;

    @Override
    public List<Category> getAllCategoryName() {
        return categoryInfoMapper.selectAll();
    }
}
