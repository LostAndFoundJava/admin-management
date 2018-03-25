package com.oukingtim.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oukingtim.domain.NewsModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public interface NewsInfoMapper extends BaseMapper<NewsModel> {


    /**
     * 编辑热门新闻
     * @param
     * @return
     */

    int editHotNews(@Param("hot") String hot);

    /**
     * 更新新闻状态,状态参考 NewsStatusEnum
     * @param delete
     * @param id
     * @return
     */

    int updateHotNewStatus(@Param("delete")String delete,@Param("id")String id);

    /**
     * 编辑热门新闻
     *
     * @param
     * @return
     */

    int editHotNews(NewsModel newsModel);

}
