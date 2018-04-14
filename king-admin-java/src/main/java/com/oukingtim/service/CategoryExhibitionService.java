package com.oukingtim.service;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.Category;
import com.oukingtim.domain.CategoryExhibition;
import com.oukingtim.domain.Exhibition;

import java.util.List;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public interface CategoryExhibitionService extends IService<CategoryExhibition> {

    List<CategoryExhibition> getExhibitions(List<String> categoryId);
}
