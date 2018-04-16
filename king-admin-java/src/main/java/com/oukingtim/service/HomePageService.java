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

    /**
     * 获取首页所有展会及相关设置信息
     * @param homepageId
     * @return
     */
    List<Exhibition> getExhibitionsByHomepageId(String homepageId);

    /**
     * 获取首页及选定的行业的所有展会及相关设置信息
     * @param homepageId
     * @param categoryIds
     * @return
     */
    List<Exhibition> getExhibitionsByHomepageId(String homepageId, List<String> categoryIds);
}
