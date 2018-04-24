package com.oukingtim.web;

import com.oukingtim.domain.FlowSrcModel;
import com.oukingtim.service.FlowSrcService;
import com.oukingtim.web.vm.ResultVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <br>创建日期：2018/4/1
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@Api(description = "用户信息execel导入")
@RequestMapping("/mgr/flowsrc/management")
public class FlowSrcController extends MgrBaseController<FlowSrcService,FlowSrcModel> {

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
                if(!flag){
                   return ResultVM.error("导入表格数据错误，请重新导入");
                }
            }
            if (list == null || list.isEmpty()) {
                return ResultVM.error("导入的excel为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
           return  ResultVM.error("导入表格数据错误，请重新导入");
        }
        return ResultVM.ok();
    }
}
