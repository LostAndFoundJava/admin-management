package com.oukingtim.web.news;

import com.oukingtim.domain.NewsModel;
import com.oukingtim.service.NewsEditionService;
import com.oukingtim.service.NewsManagementService;
import com.oukingtim.web.MgrBaseController;
import com.oukingtim.web.vm.ResultVM;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br>创建日期：2018/3/22
 * <br>Copyright 2018　上海华信证券　All Rights Reserved</b>
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RequestMapping("/mgr/news/management")
public class NewsEditionController extends MgrBaseController<NewsEditionService,NewsModel> {


}
