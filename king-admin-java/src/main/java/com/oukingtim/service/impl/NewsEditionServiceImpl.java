package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.NewsModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Mapper
public interface NewsEditionServiceImpl extends IService<NewsModel>{

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

    boolean updateHotNewStatus(String delete, String id);






}
