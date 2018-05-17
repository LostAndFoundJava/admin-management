package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.Exhibition;
import com.oukingtim.domain.FlowSrcModel;
import com.oukingtim.domain.SysUser;
import com.oukingtim.mapper.FlowSrcMapper;
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

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class FlowSrcServiceImpl extends ServiceImpl<FlowSrcMapper, FlowSrcModel> implements FlowSrcService {

    private static final String SRC_FROM_0 = "0";

    @Autowired
    private ReadExcel readExcel;

    @Autowired
    private MgrExhibitionService mgrExhibitionService;

    @Override
    public List<FlowSrcModel> importExcel(String fileName, MultipartFile file) {
        List<FlowSrcModel> list = readExcel.gotExcelInfo(fileName, file);
        return list;
    }


    @Override
    public Page<FlowSrcModel> selectPage(Page<FlowSrcModel> page, Wrapper<FlowSrcModel> wrapper) {
        super.selectPage(page, wrapper);
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (page != null && !CollectionUtils.isEmpty(page.getRecords())) {
            for (FlowSrcModel flowSrcModel : page.getRecords()) {
//                if ("0".equals(flowSrcModel.getSrcType())) {
                //没有权限走脱敏渠道
                if (user != null && StringUtils.isNotBlank(user.getDesensitization()) && "0".equals(user.getDesensitization())) {
                    //手机号
                    if (StringUtils.isNotBlank(flowSrcModel.getMobileNo())) {
                        flowSrcModel.setMobileNo(flowSrcModel.getMobileNo().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                    }
                    //客户姓名
                    if (StringUtils.isNotBlank(flowSrcModel.getClientName())) {
                        flowSrcModel.setClientName((flowSrcModel.getClientName()).substring(0, 1) + "**");
                    }
                    //邮箱
                        /*if (StringUtils.isNotBlank(flowSrcModel.getEmail())) {
                            String[] emailArr = flowSrcModel.getEmail().split("@");
                            if (StringUtils.isNotBlank(emailArr[emailArr.length - 1])) {
                                flowSrcModel.setEmail("****" + "@" + emailArr[emailArr.length - 1]);
                            }
                        }*/
                    String email = flowSrcModel.getEmail();
                    if (StringUtils.isNotBlank(email)) {
                        int atIndex = email.trim().lastIndexOf("@");
                        if (atIndex > 0) {
                            String suffix = email.trim().substring(atIndex);
                            flowSrcModel.setEmail("****" + suffix);
                        }
                    }

                    //地址
                    if (StringUtils.isNotBlank(flowSrcModel.getAddress())) {
                        flowSrcModel.setAddress("*****");
                    }
                }
                    /*List<Exhibition> list = mgrExhibitionService.selectTitleById();
                    if (list != null && !list.isEmpty()) {
                        for (Exhibition e : list) {
                            if (flowSrcModel.getExhibition() != null && !flowSrcModel.getExhibition().isEmpty()) {
                                if (flowSrcModel.getExhibition().equals(e.getId())) {
                                    flowSrcModel.setExhibition(e.getTitle());
                                    break;
                                }
                            }
                        }
                    }*/
                if (flowSrcModel.getExhibition() != null && SRC_FROM_0.equals(flowSrcModel.getSrcType())) {
                    Exhibition exhibition = mgrExhibitionService.selectById(flowSrcModel.getExhibition());
                    if (exhibition != null) {
                        flowSrcModel.setExhibition(exhibition.getTitle());
                    }
                }

            }
//            }
        }
        return page;
    }
}
