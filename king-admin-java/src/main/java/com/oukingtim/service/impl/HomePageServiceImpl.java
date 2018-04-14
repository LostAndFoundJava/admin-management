package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.Category;
import com.oukingtim.domain.CategoryExhibition;
import com.oukingtim.domain.Exhibition;
import com.oukingtim.domain.HomePageHotConfig;
import com.oukingtim.mapper.*;
import com.oukingtim.service.HomePageService;
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
public class HomePageServiceImpl extends ServiceImpl<HomePageMapper, HomePageHotConfig> implements HomePageService {


    @Autowired
    private CategoryInfoMapper categoryInfoMapper;

    @Autowired
    private HomePageCategoryMapper homePageCategoryMapper;

    @Autowired
    private MgrExhibitionMapper mgrExhibitionMapper;

    @Autowired
    private CategoryExhibitionMapper categoryExhibitionMapper;

    @Override
    public List<Exhibition> getExhibitionsByHomepageId(String homepageId) {
        List<Exhibition> exhibitions = mgrExhibitionMapper.selectList(new EntityWrapper<>());
        for(Exhibition exhibition : exhibitions) {
            Category category = categoryInfoMapper.selectById(exhibition.getCategoryId());
            if (category != null) {
                exhibition.setCategoryName(category.getName());
            }
        }
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
    }
}
