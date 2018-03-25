package com.oukingtim.service;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.Exhibition;
import com.oukingtim.domain.NewsModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Mapper
public interface NewsEditionService extends IService<NewsModel>{

    /**
     * 编辑热门新闻
     * @param
     * @return
     */

    boolean editHotNews(String hot);

    /**
     * 更新新闻状态,状态参考 NewsStatusEnum
     * @param delete
     * @param id
     * @return
     */

    boolean updateHotNewStatus(String delete,String id);






}
