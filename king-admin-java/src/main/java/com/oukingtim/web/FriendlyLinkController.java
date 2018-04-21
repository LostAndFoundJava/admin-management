package com.oukingtim.web;

import com.oukingtim.domain.FriendlyLinkModel;
import com.oukingtim.service.FriendlyLinkService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>创建日期：2018/4/21
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@Api(description = "友情链接")
@RequestMapping("/mgr/link/management")
public class FriendlyLinkController extends MgrBaseController<FriendlyLinkService,FriendlyLinkModel>{

    Logger LOGGER = LoggerFactory.getLogger(FriendlyLinkController.class);


}
