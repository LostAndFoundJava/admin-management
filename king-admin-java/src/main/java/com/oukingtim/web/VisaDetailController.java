package com.oukingtim.web;

import com.oukingtim.domain.VisaModel;
import com.oukingtim.service.VisaDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>创建日期：2018/4/17
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@RequestMapping("/mgr/visa/management")
public class VisaDetailController extends MgrBaseController<VisaDetailService, VisaModel> {


}
