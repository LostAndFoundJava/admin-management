package com.oukingtim.service;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.Exhibition;
import com.oukingtim.domain.HomePageHotConfig;

import java.util.List;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public interface HomePageService extends IService<HomePageHotConfig> {


    List<Exhibition> getExhibitionsByHomepageId(String homepageId);
}
