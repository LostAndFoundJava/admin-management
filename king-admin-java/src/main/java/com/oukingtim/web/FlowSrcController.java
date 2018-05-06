package com.oukingtim.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.oukingtim.domain.FlowSrcModel;
import com.oukingtim.domain.SysUser;
import com.oukingtim.service.FlowSrcService;
import com.oukingtim.util.BizException;
import com.oukingtim.util.ShiroUtils;
import com.oukingtim.util.StringTools;
import com.oukingtim.web.vm.ResultVM;
import com.oukingtim.web.vm.SmartPageVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <br>创建日期：2018/4/1
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@Api(description = "用户信息execel导入")
@RequestMapping("/mgr/flowsrc/management")
public class FlowSrcController {

    private static Logger LOGGER = LoggerFactory.getLogger(FlowSrcController.class);

    @Autowired
    private FlowSrcService flowSrcService;

    @ApiOperation(value = "导入excel", notes = "导入excel")
    @RequestMapping(value = "/excelupload")
    public ResultVM importFlowSrc(HttpServletRequest request, HttpServletResponse response, MultipartFile[] files) {

        if (request == null) {
            LOGGER.info("导入excel出错");
            return ResultVM.error("导入数据出错,请重新导入");
        }
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multipartRequest.getFileNames();
            List<FlowSrcModel> list = new ArrayList<>();
            while (iter.hasNext()) {
                String fileName;
                MultipartFile file = multipartRequest.getFile(iter.next());
                fileName = file.getOriginalFilename();
                list = flowSrcService.importExcel(fileName, file);
                Boolean flag = flowSrcService.insertBatch(list);
                if (!flag) {
                    return ResultVM.error("导入表格数据错误，请重新导入");
                }
            }
            if (list == null || list.isEmpty()) {
                return ResultVM.error("导入的excel为空");
            }
        } catch (BizException e) {
            return ResultVM.error(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVM.error("导入表格数据错误，请重新导入");
        }
        return ResultVM.ok();
    }


    @PostMapping("/flowsrcs")
    public ResultVM getSmartData(@RequestBody SmartPageVM<FlowSrcModel> spage) {

        //获取当前用户
        SysUser user = ShiroUtils.getUser();
        //用户关联渠道
        String src = user.getChannel();
        Page<FlowSrcModel> page = new Page<>(spage.getPagination().getStart()
                , spage.getPagination().getNumber());

        if (StringUtils.isBlank(spage.getSort().getPredicate())) {
            spage.getSort().setPredicate("update_time");
        }
        page.setOrderByField(spage.getSort().getPredicate());
        page.setAsc(spage.getSort().getReverse());
        EntityWrapper<FlowSrcModel> wrapper = new EntityWrapper<>();

        //设置渠道
        if(StringUtils.isNotEmpty(src)) {
            wrapper.like("SRC", src);
        }

        if (spage.getSearch() != null) {
            Field[] fields = spage.getSearch().getClass().getDeclaredFields();
            Object startTime = new Date(0);
            Object endTime = new Date();
            for (int i = 0; i < fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                    Object value = fields[i].get(spage.getSearch());
                    if (null != value && !value.equals("")) {
                        String fieldname = StringTools.underscoreName(fields[i].getName());
                        if (!"START_TIME".equals(fieldname) && !"END_TIME".equals(fieldname)) {
                            wrapper.like(fieldname, value.toString());
                        }
                        if ("START_TIME".equals(fieldname)) {
                            startTime = value;
                        }

                        if ("END_TIME".equals(fieldname)) {
                            endTime = value;
                        }


                    }
                    fields[i].setAccessible(false);
                } catch (Exception e) {
                    LOGGER.error("查询列表异常-{}", page.toString(), e);
                    return ResultVM.error("查询失败");
                }
            }
            wrapper.between("CREATE_TIME", startTime, endTime);
        }
        return ResultVM.ok(flowSrcService.selectPage(page, wrapper));
    }

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public ResultVM export(@RequestBody SmartPageVM<FlowSrcModel> spage) {
        ResultVM resultVM = getSmartData(spage);


        return ResultVM.ok();
    }

}
