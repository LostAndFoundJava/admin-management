package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.Exhibition;
import com.oukingtim.domain.FlowSrcModel;
import com.oukingtim.domain.SysUser;
import com.oukingtim.mapper.FlowSrcMapper;
import com.oukingtim.service.ChannelService;
import com.oukingtim.service.FlowSrcService;
import com.oukingtim.service.MgrExhibitionService;
import com.oukingtim.util.ReadExcel;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class FlowSrcServiceimpl extends ServiceImpl<FlowSrcMapper, FlowSrcModel> implements FlowSrcService {

    @Autowired
    private ReadExcel readExcel;

    @Autowired
    private MgrExhibitionService mgrExhibitionService;

    @Autowired
    private ChannelService channelService;

    @Override
    public List<FlowSrcModel> importExcel(String fileName, MultipartFile file) {
        List<FlowSrcModel> list = readExcel.gotExcelInfo(fileName, file);
        return list;
    }

    @Override
    public Page<FlowSrcModel> selectDataByChannel(Page<FlowSrcModel> page, Wrapper<FlowSrcModel> wrapper) {

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> condition = page.getCondition();
        if (!condition.isEmpty()) {
            condition.put("channel", user.getChannel());
            page.setCondition(condition);
        }
        super.selectPage(page, wrapper);
        Page<FlowSrcModel> pages = new Page<>();
        if (page != null && !CollectionUtils.isEmpty(page.getRecords())) {
            for (FlowSrcModel flowSrcModel : page.getRecords()) {
                if ("0".equals(flowSrcModel.getSrcType())) {
                    //没有权限走脱敏渠道
                    if (user != null && StringUtils.isNotBlank(user.getDesensitization()) && "0".equals(user.getDesensitization())) {
                        if (StringUtils.isNotBlank(flowSrcModel.getMobileNo())) {
                            flowSrcModel.setMobileNo(flowSrcModel.getMobileNo().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                            //客户姓名
                            if (StringUtils.isNotBlank(flowSrcModel.getClientName())) {
                                flowSrcModel.setClientName((flowSrcModel.getClientName()).substring(0) + "**");
                            }
                            //客户姓名
                            if (StringUtils.isNotBlank(flowSrcModel.getClientName())) {
                                flowSrcModel.setClientName(flowSrcModel.getClientName().substring(0) + "**");
                            }
                            //邮箱
                            if (StringUtils.isNotBlank(flowSrcModel.getEmail())) {
                                String[] emailArr = flowSrcModel.getEmail().split("@");
                                if (StringUtils.isNotBlank(emailArr[emailArr.length - 1])) {
                                    flowSrcModel.setEmail("****" + "@" + emailArr[emailArr.length - 1]);
                                }
                            }
                        }
                    }
                }
                List<Exhibition> exhibitions = mgrExhibitionService.selectTitleById();
                if (exhibitions != null && !exhibitions.isEmpty()) {
                    for (Exhibition e : exhibitions) {
                        if (flowSrcModel.getExhibition() != null && !flowSrcModel.getExhibition().isEmpty()) {
                            if (flowSrcModel.getExhibition().equals(e.getId())) {
                                flowSrcModel.setExhibition(e.getTitle());
                                break;
                            }
                        }
                    }
                }
            }
        }
        return page;
    }


    @Override
    public Page<FlowSrcModel> selectPage(Page<FlowSrcModel> page, Wrapper<FlowSrcModel> wrapper) {
        super.selectPage(page, wrapper);
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (page != null && !CollectionUtils.isEmpty(page.getRecords())) {
            for (FlowSrcModel flowSrcModel : page.getRecords()) {
                if ("0".equals(flowSrcModel.getSrcType())) {


                    List<Exhibition> list = mgrExhibitionService.selectTitleById();
                    if (list != null && !list.isEmpty()) {
                        for (Exhibition e : list) {
                            if (flowSrcModel.getExhibition() != null && !flowSrcModel.getExhibition().isEmpty()) {
                                if (flowSrcModel.getExhibition().equals(e.getId())) {
                                    flowSrcModel.setExhibition(e.getTitle());
                                    break;
                                }
                            }
                        }
                    }
                }
                //没有权限走脱敏渠道
                if (user != null && StringUtils.isNotBlank(user.getDesensitization()) && "0".equals(user.getDesensitization())) {
                    //手机号
                    if (StringUtils.isNotBlank(flowSrcModel.getMobileNo())) {
                        flowSrcModel.setMobileNo(flowSrcModel.getMobileNo().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                    }
                    //客户姓名
                    if (StringUtils.isNotBlank(flowSrcModel.getClientName())) {
                        flowSrcModel.setClientName((flowSrcModel.getClientName()).substring(0,1) + "**");
                    }
                    //邮箱
                    if (StringUtils.isNotBlank(flowSrcModel.getEmail())) {
                        String[] emailArr = flowSrcModel.getEmail().split("@");
                        if (StringUtils.isNotBlank(emailArr[emailArr.length - 1])) {
                            flowSrcModel.setEmail("****" + "@" + emailArr[emailArr.length - 1]);
                        }
                    }
                    //地址
                    if (StringUtils.isNotBlank(flowSrcModel.getAddress())) {
                        flowSrcModel.setAddress("*****");
                    }
                }
            }

        }
        return page;
    }
}
