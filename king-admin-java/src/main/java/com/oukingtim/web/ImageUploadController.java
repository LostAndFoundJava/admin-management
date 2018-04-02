package com.oukingtim.web;

import com.oukingtim.util.CkUploadUtils;
import com.oukingtim.util.Constants;
import com.oukingtim.util.DateUtil;
import com.oukingtim.web.vm.ResultVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xufan on 2018/03/23.
 */
@RestController
@Api(description = "图片上传接口")
@RequestMapping("/api/mgr/image")
public class ImageUploadController {
    private static Logger logger = LoggerFactory.getLogger(ImageUploadController.class);


    @ApiOperation(value = "上传图片", notes = "上传图片")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultVM uploadImg(HttpSession session, HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
        List<String> uploadFileUrls;
        List<String> fileUrlVO = new ArrayList<>();
        String fastIp = Constants.FILE_SERVER_ADMIN;
        String savePath = session.getServletContext().getRealPath("/");
        String filePath = "/" + DateUtil.date2Str("yyyy/MM/dd", new Date());//当前日期作为文件目录
        try {
            uploadFileUrls = CkUploadUtils.upload(request, savePath, filePath);

            if (!CollectionUtils.isEmpty(uploadFileUrls)) {
                for (String uploadFileUrl : uploadFileUrls) {
                    uploadFileUrl = fastIp + "/" + uploadFileUrl;
                    fileUrlVO.add(uploadFileUrl);
                }
            }
        } catch (IOException e) {
            logger.error("上传出错", e);
            return ResultVM.error("上传出错");
        }
        /*try {
            CkUploadUtils.ckeditor(request, response, fastIp, savePath);
        } catch (IOException e) {
            logger.error("上传出错", e);
        }*/
        return ResultVM.ok(fileUrlVO);
    }

}
