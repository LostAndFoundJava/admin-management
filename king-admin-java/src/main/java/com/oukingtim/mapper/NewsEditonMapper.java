package com.oukingtim.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oukingtim.domain.Category;
import com.oukingtim.domain.NewsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Mapper
public interface NewsEditonMapper extends BaseMapper<NewsModel> {
    List<NewsModel> selectAll();
}
