package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.*;
import com.oukingtim.mapper.*;
import com.oukingtim.service.CategoryService;
import com.oukingtim.service.HomePageService;
import com.oukingtim.service.MgrExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
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

    @Autowired
    private MgrExhibitionService mgrExhibitionService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Exhibition> getExhibitionsByHomepageId(String homepageId) {
        //查询所有展会
        List<Exhibition> exhibitions = mgrExhibitionMapper.selectList(new EntityWrapper<>());
        for (Exhibition exhibition : exhibitions) {
            Category category = categoryInfoMapper.selectById(exhibition.getCategoryId());
            if (category != null) {
                exhibition.setCategoryName(category.getName());
            }
        }

        //查询当前首页的展会
        if (homepageId != null) {
            CategoryExhibition categoryExhibition = new CategoryExhibition();
            categoryExhibition.setHomepageId(homepageId);
            EntityWrapper wrapper = new EntityWrapper(categoryExhibition);
            List<CategoryExhibition> categoryExhibitions = categoryExhibitionMapper.selectList(wrapper);
                for (CategoryExhibition categoryExhibition1 : categoryExhibitions) {
                    for (Exhibition exhibition : exhibitions) {
                        if (exhibition.getId().equals(categoryExhibition1.getExhibitionId())) {
                            if (Integer.valueOf(1).equals(categoryExhibition1.getIsCarousel())) {
                                exhibition.setHasCarousel(categoryExhibition1.getIsCarousel());
                            }
                            if (Integer.valueOf(1).equals(categoryExhibition1.getIsHot())) {
                                exhibition.setHot(categoryExhibition1.getIsHot());
                            }
                            if (Integer.valueOf(1).equals(categoryExhibition1.getIsChoice())) {
                                exhibition.setIsChoice(categoryExhibition1.getIsChoice());
                            }
                        break;
                    }
                }
            }

            /*List<String> categoryIds = homePageCategoryMapper.selectCategoryIdsByHomepageId(homepageId);
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
            }*/
        }
        return exhibitions;
    }

    @Override
    public List<Exhibition> getExhibitionsByHomepageId(String homepageId, List<String> categoryIds) {
        List<Exhibition> exhibitions = null;
        if (!CollectionUtils.isEmpty(categoryIds)) {
            exhibitions = mgrExhibitionService.getExhibitionsByCategoryId(categoryIds);
        } else {
            exhibitions = mgrExhibitionMapper.selectList(new EntityWrapper<>());
        }
        for (Exhibition exhibition : exhibitions) {
            Category category = categoryInfoMapper.selectById(exhibition.getCategoryId());
            if (category != null) {
                exhibition.setCategoryName(category.getName());
            }
        }
        if (homepageId != null) {
            List<String> categoryIdList = homePageCategoryMapper.selectCategoryIdsByHomepageId(homepageId);
            if (!CollectionUtils.isEmpty(categoryIdList)) {
                for (String categoryId : categoryIdList) {
                    EntityWrapper wrapper = new EntityWrapper(new CategoryExhibition(categoryId));
                    List<CategoryExhibition> categoryExhibitions = categoryExhibitionMapper.selectList(wrapper);
                    for (CategoryExhibition categoryExhibition : categoryExhibitions) {
                        for (Exhibition exhibition : exhibitions) {
                            if (exhibition.getId().equals(categoryExhibition.getExhibitionId())) {
                                if (Integer.valueOf(1).equals(categoryExhibition.getIsCarousel())) {
                                    exhibition.setHasCarousel(categoryExhibition.getIsCarousel());
                                }
                                if (Integer.valueOf(1).equals(categoryExhibition.getIsHot())) {
                                    exhibition.setHot(categoryExhibition.getIsHot());
                                }
                                if (Integer.valueOf(1).equals(categoryExhibition.getIsChoice())) {
                                    exhibition.setIsChoice(categoryExhibition.getIsChoice());
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        return exhibitions;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(HomePageHotConfig t) {
        boolean flag = false;
        if (t != null) {
            t.setDelete(0);
            flag = super.insert(t);
            List<CategoryExhibition> categoryExhibitions = t.getCategoryExhibitionList();
            List<HomePageCategory> homePageCategories = t.getHomePageCategoryList();
            if (flag) {
                if (!CollectionUtils.isEmpty(homePageCategories)) {
                    for (HomePageCategory homePageCategory : homePageCategories) {
                        homePageCategory.setCreateTime(new Date());
                        homePageCategory.setUpdateTime(new Date());
                        homePageCategory.setDelete(0);
                        homePageCategory.setHomepageId(t.getId());
                        homePageCategoryMapper.insert(homePageCategory);
                    }
                }

                if (!CollectionUtils.isEmpty(categoryExhibitions)) {
                    for (CategoryExhibition categoryExhibition : categoryExhibitions) {
                        categoryExhibition.setHomepageId(t.getId());
                        categoryExhibition.setCreateTime(new Date());
                        categoryExhibition.setUpdateTime(new Date());
                        categoryExhibition.setDelete(0);
                        categoryExhibitionMapper.insert(categoryExhibition);
                    }
                }
            }

        }

        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(HomePageHotConfig t) {
        boolean flag = false;
        if (t != null) {
            String homepageId = t.getId();
            //1、更新首页设置
            flag = super.updateById(t);

            //2、删除所有已有的数据
            List<CategoryExhibition> categoryExhibitions = t.getCategoryExhibitionList();
            List<HomePageCategory> homePageCategories = t.getHomePageCategoryList();
            if (flag) {

                //2.1删除所有精选行业
                CategoryExhibition categoryExhibition = new CategoryExhibition();
                categoryExhibition.setHomepageId(homepageId);
                categoryExhibitionMapper.delete(new EntityWrapper<>(categoryExhibition));
                if (!CollectionUtils.isEmpty(categoryExhibitions)) {
                    for (CategoryExhibition categoryExhibition1 : categoryExhibitions) {
                        categoryExhibition1.setHomepageId(t.getId());
                        categoryExhibition1.setCreateTime(new Date());
                        categoryExhibition1.setUpdateTime(new Date());
                        categoryExhibition1.setDelete(0);
                        categoryExhibitionMapper.insert(categoryExhibition1);
                    }
                }

                //2.2删除首页选择的所有展会
                HomePageCategory homePageCategory = new HomePageCategory();
                homePageCategory.setHomepageId(homepageId);
                homePageCategoryMapper.delete(new EntityWrapper<>(homePageCategory));
                if (!CollectionUtils.isEmpty(homePageCategories)) {
                    for (HomePageCategory homePageCategory1 : homePageCategories) {
                        homePageCategory1.setCreateTime(new Date());
                        homePageCategory1.setUpdateTime(new Date());
                        homePageCategory1.setDelete(0);
                        homePageCategory1.setHomepageId(homepageId);
                        homePageCategoryMapper.insert(homePageCategory1);
                    }
                }


            }

        }

        return flag;
    }

}
