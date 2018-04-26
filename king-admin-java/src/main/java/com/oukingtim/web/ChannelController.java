package com.oukingtim.web;

import com.oukingtim.domain.ChannelModel;
import com.oukingtim.service.ChannelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>创建日期：2018/4/26
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@RequestMapping(value ="mgr/channel/management")
public class ChannelController extends MgrBaseController<ChannelService,ChannelModel>{
}
