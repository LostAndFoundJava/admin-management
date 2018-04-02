package com.oukingtim.web.news;

import com.oukingtim.domain.NewsModel;
import com.oukingtim.service.NewsManagementService;
import com.oukingtim.web.MgrBaseController;
import com.oukingtim.web.vm.ResultVM;
import com.oukingtim.web.vm.SmartPageVM;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br>创建日期：2018/3/22
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RequestMapping("/mgr/news/management")
public class NewsManagementController extends MgrBaseController<NewsManagementService,NewsModel> {

    //新闻编辑
    public ResultVM editNews() {
        return null;
    }

    //新闻发布
    public ResultVM publishNews() {
        return null;
    }

    //新闻上线
    public ResultVM nolineNews() {
        return null;
    }

    //新闻上线
    public ResultVM onlineNews() {
        return null;
    }
    //新闻行业类别查询
    public ResultVM queryNewsIndustry() {
        return null;
    }
}
