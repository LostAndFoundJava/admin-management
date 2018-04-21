package com.oukingtim.web;

import com.oukingtim.domain.File;
import com.oukingtim.service.FileService;
import com.oukingtim.web.vm.ResultVM;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <br>创建日期：2018/4/17
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@RequestMapping("/api/mgr/visaUrl")
public class VisaUrlController {
    @Autowired
    private FileService fileService;

    @ApiOperation(value = "查询签证存储信息", notes = "查询签证存储信息")
    @RequestMapping(value = "/url")
    public ResultVM uploadExcel() {
        List<File> list = fileService.selectVisaInfo();
        return ResultVM.ok(list);
    }
}
