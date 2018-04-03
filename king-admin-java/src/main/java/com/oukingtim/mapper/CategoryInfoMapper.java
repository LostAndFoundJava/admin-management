package com.oukingtim.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.oukingtim.domain.Category;
import org.apache.ibatis.annotations.*;


import java.util.List;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Mapper
public interface CategoryInfoMapper extends BaseMapper<Category> {
    List<Category> selectAll();
}
