package com.oukingtim.web;

import com.oukingtim.util.ReadExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <br>创建日期：2018/4/14
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@Api(description = "用户信息execel导入")
@RequestMapping("/api/mgr/excel")
public class ExcelUploadController {

    @Autowired
    private ReadExcel readExcel;

    @ApiOperation(value = "导入excel", notes = "导入excel")
    @RequestMapping(value = "/excelupload")
    public String uploadExcel(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                            @RequestParam String CKEditor) {
        String uploadFile = "";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("通过 jquery.form.js 提供的ajax方式上传文件！");
        MultipartFile file = multipartRequest.getFile("upfile");
        String fileName = file.getName();
        readExcel.gotExcelInfo(fileName, file);

        return uploadFile;
    }
}
