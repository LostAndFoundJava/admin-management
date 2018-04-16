package com.oukingtim.web;

import com.oukingtim.service.VisaSignService;
import com.oukingtim.web.vm.ResultVM;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <br>创建日期：2018/4/15
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RestController
@RequestMapping("/api/mgr/visa")
public class VisaManagementController {
    @Autowired
    private VisaSignService visaSignService;

    @ApiOperation(value = "上传签证文件", notes = "上传签证文件")
    @RequestMapping(value = "/visaupload")
    public ResultVM uploadExcel(HttpServletRequest request, HttpServletResponse response, MultipartFile[] files) {
        Map<String, String> map = new HashMap<>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> iter = multipartRequest.getFileNames();
        while (iter.hasNext()) {
            String fileName;
            MultipartFile file = multipartRequest.getFile(iter.next());
            String filePath = "/Users/JackieChan/visa";
            map = visaSignService.uploadFile(filePath, file);
        }
        if (map == null ||map.isEmpty()|| (!map.isEmpty() && "1".equals(map.get("error")))){
            return ResultVM.error("上传文件错误，请重新上传");
        }
        return ResultVM.ok();
    }

}
