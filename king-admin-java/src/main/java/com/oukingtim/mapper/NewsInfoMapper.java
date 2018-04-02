package com.oukingtim.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oukingtim.domain.NewsModel;
import org.apache.ibatis.annotations.*;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
//@Mapper
public interface NewsInfoMapper extends BaseMapper<NewsModel> {


//    /**
//     * 编辑热门新闻
//     * @param
//     * @return
//     */
//    @Select(
//            "select * from news  t" +
//                    "where 1=1 "+
//                    "<if \"test=t.hot != null and t.hot !=''\" >" +
//                    "and t.hot = #{hot}" +
//                    "</if>" +
//                    "order by t.hot_level asc;"
//    )
//    int editHotNews(@Param("hot") String hot);

//    /**
//     * 更新新闻状态,状态参考 NewsStatusEnum
//     * @param delete
//     * @param id
//     * @return
//     */
//    @Update(
//            "UPDATE news" +
//                    "SET delete=#{delete}" +
//                    "WHERE id=#{id};"
//    )
//    int updateHotNewStatus(@Param("delete")String delete,@Param("id")String id);
//
//    /**
//     * 编辑热门新闻
//     *
//     * @param
//     * @return
//     */
//    @Insert(
//            "insert into news values(#{categoryId},#{content},#{original},#{hot},#{hotLevel},#{createTime},#{updateTime},#{delete})"
//    )
//    int editHotNews(NewsModel newsModel);

}
