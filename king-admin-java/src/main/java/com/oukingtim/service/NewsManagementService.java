package com.oukingtim.service;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.NewsModel;
import org.apache.ibatis.annotations.Insert;

import java.util.Date;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public interface NewsManagementService extends IService<NewsModel> {


    int editHotNews(NewsModel newsModel);




}
