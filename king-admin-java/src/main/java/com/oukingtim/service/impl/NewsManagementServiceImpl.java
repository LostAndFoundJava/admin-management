package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.NewsModel;
import org.springframework.stereotype.Service;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public interface NewsManagementServiceImpl extends IService<NewsModel> {


    int editHotNews(NewsModel newsModel);




}
