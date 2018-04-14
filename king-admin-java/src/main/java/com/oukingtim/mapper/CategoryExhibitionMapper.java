package com.oukingtim.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oukingtim.domain.CategoryExhibition;
import com.oukingtim.domain.HomePageCategory;

import java.util.List;

/**
 * Created by xufan on 2018/03/19.
 */
public interface CategoryExhibitionMapper extends BaseMapper<CategoryExhibition> {
    List<String> selectExhibitionIdsByCategoryId(String categoryId);
}
