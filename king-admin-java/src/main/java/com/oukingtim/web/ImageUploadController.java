package com.oukingtim.web;

import com.oukingtim.util.CkUploadUtils;
import com.oukingtim.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by xufan on 2018/03/23.
 */
@RestController
@Api(description = "图片上传接口")
@RequestMapping("/api/mgr/image")
public class ImageUploadController {
    private static Logger logger = LoggerFactory.getLogger(ImageUploadController.class);


    @ApiOperation(value = "上传图片", notes = "上传图片")
    @RequestMapping(value = "/upload")
    public static String uploadImg(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam String CKEditor, @RequestParam String CKEditorFuncNum, @RequestParam String langCode) {
        String uploadFile = "";
        String fastIp = Constants.FILE_SERVER_ADMIN;
        String savePath = session.getServletContext().getRealPath("/");
        try {
            CkUploadUtils.ckeditor(request, response, fastIp, savePath);
        } catch (IOException e) {
            logger.error("上传出错", e);
        }
        return uploadFile;
    }


}
