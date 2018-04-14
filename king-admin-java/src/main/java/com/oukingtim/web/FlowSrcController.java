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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <br>创建日期：2018/4/1
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@Api(description = "用户信息execel导入")
@RequestMapping("/api/mgr/image")
public class FlowSrcController {

    private static Logger LOGGER = LoggerFactory.getLogger(FlowSrcController.class);

    @Autowired
    private FlowSrcService flowSrcService;

    @ApiOperation(value = "导入excel", notes = "导入excel")
    @RequestMapping(value = "/importexcel")
    public ResultVM importFlowSrc(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) {

        if (file == null) {
            LOGGER.info("导入excel出错");
            return ResultVM.error("导入数据出错,请重新导入");
        }
        String fileName = file.getName();
        List<FlowSrcModel> list = flowSrcService.importExcel(fileName, file);
        if (list == null || list.isEmpty()) {
            return ResultVM.error("导入的excel为空");
        }
        return ResultVM.ok();
    }
}
