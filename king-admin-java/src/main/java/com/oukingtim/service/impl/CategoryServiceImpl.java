package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.Category;
import com.oukingtim.domain.CategoryExhibition;
import com.oukingtim.domain.Exhibition;
import com.oukingtim.mapper.*;
import com.oukingtim.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

<<<<<<< HEAD
    @Autowired
    private HomePageMapper homePageMapper;

    @Autowired
    private HomePageCategoryMapper homePageCategoryMapper;

    @Autowired
    private MgrExhibitionMapper mgrExhibitionMapper;

    @Autowired
    private CategoryExhibitionMapper categoryExhibitionMapper;

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
=======
>>>>>>> bdaadb568b5c124bd820fdcd4b83afc8ce4ccfae
    @Override
    public List<Category> getAllCategoryName() {
        return categoryInfoMapper.selectAll();
    }
<<<<<<< HEAD

    @Override
    public List<Category> getList(String homepageId) {
        List<Category> categoryList = selectList(new EntityWrapper<>());
        if (homepageId != null) {
            List<String> categoryIds = homePageCategoryMapper.selectCategoryIdsByHomepageId(homepageId);
            if (!CollectionUtils.isEmpty(categoryIds)) {
                for (Category category : categoryList) {
                    if (categoryIds.contains(category.getId())) {
                        category.setChecked(true);
                    }
                }
            }
        }
        return categoryList;
    }

    /*@Override
    public List<Exhibition> getExhibitionsByHomepageId(String homepageId) {
        List<Exhibition> exhibitions = mgrExhibitionMapper.selectList(new EntityWrapper<>());
        if (homepageId != null) {
            List<String> categoryIds = homePageCategoryMapper.selectCategoryIdsByHomepageId(homepageId);
            if (!CollectionUtils.isEmpty(categoryIds)) {
                for (String categoryId : categoryIds) {
                    EntityWrapper wrapper = new EntityWrapper(new CategoryExhibition(categoryId));
                    List<CategoryExhibition> categoryExhibitions = categoryExhibitionMapper.selectList(wrapper);
                    for (CategoryExhibition categoryExhibition : categoryExhibitions) {
                        for (Exhibition exhibition : exhibitions) {
                            if (exhibition.getId().equals(categoryExhibition.getExhibitionId())) {
                                exhibition.setHasCarousel(categoryExhibition.getIsCarousel());
                                exhibition.setHot(categoryExhibition.getIsHot());
                                exhibition.setIsChoice(categoryExhibition.getIsChoice());
                            }
                        }
                    }
                }
            }
        }
        return exhibitions;
    }*/


=======
>>>>>>> bdaadb568b5c124bd820fdcd4b83afc8ce4ccfae
}
