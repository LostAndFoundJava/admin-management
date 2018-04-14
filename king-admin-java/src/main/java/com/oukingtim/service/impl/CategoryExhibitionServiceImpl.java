package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.Category;
import com.oukingtim.domain.CategoryExhibition;
import com.oukingtim.domain.Exhibition;
import com.oukingtim.mapper.CategoryExhibitionMapper;
import com.oukingtim.mapper.CategoryInfoMapper;
import com.oukingtim.mapper.MgrExhibitionMapper;
import com.oukingtim.service.CategoryExhibitionService;
import com.oukingtim.service.CategoryService;
import com.oukingtim.service.MgrExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public class CategoryExhibitionServiceImpl extends ServiceImpl<CategoryExhibitionMapper, CategoryExhibition> implements CategoryExhibitionService {

    @Autowired
    private MgrExhibitionMapper mgrExhibitionMapper;

    @Autowired
    private MgrExhibitionService mgrExhibitionService;

    @Override
    public List<CategoryExhibition> getExhibitions(List<String> categoryIds) {
        List<CategoryExhibition> categoryExhibitionList = new ArrayList<>();
        //获取categoryIds的所有展会
        List<Exhibition> exhibitions = mgrExhibitionService.getExhibitionsByCategoryId(categoryIds);

        //获取首页的所有展会
        EntityWrapper<Exhibition> wrapper = new EntityWrapper<>();
        for (String categoryId : categoryIds) {
            wrapper.or().in("category_id", categoryId);
        }
        List<CategoryExhibition> categoryExhibitions = selectList(new EntityWrapper<>());

        if(!CollectionUtils.isEmpty(categoryExhibitions)){
            for(CategoryExhibition categoryExhibition : categoryExhibitions){
                Exhibition exhibition = mgrExhibitionService.selectById(categoryExhibition.getExhibitionId());
                if(exhibition != null){
                    categoryExhibition.setExhibition(exhibition);
                }
            }
        }

        return categoryExhibitions;
    }
}
