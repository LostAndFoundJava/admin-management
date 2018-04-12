package com.oukingtim.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oukingtim.domain.HomePageCategory;
import com.oukingtim.domain.HomePageHotConfig;

import java.util.List;

/**
 * Created by xufan on 2018/03/19.
 */
public interface HomePageCategoryMapper extends BaseMapper<HomePageCategory> {
    List<String> selectCategoryIdsByHomepageId(String homepageId);
}
