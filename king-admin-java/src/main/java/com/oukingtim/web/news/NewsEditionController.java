package com.oukingtim.web.news;

import com.oukingtim.domain.NewsModel;
import com.oukingtim.service.NewsEditionService;
import com.oukingtim.web.MgrBaseController;
import com.oukingtim.web.vm.ResultVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <br>创建日期：2018/3/22
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@RequestMapping("/mgr/news/management")
public class NewsEditionController extends MgrBaseController<NewsEditionService,NewsModel> {
    @Autowired
    private NewsEditionService newsEditionService;

    @RequestMapping("/all")
    public ResultVM selectAll(){
        List<NewsModel> newsModels = newsEditionService.selectAll();
        return ResultVM.ok(newsModels);
    }
}
