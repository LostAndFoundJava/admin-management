package com.oukingtim.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <br>创建日期：2018/4/15
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public class FileUploadUtil {

    //上传文件专用
    public static Map<String, String> uploadFile(MultipartFile multipartFile, String filePath) {
        Map<String, String> resultMap = new HashMap<>();
        String fileName = multipartFile.getOriginalFilename();
        InputStream is = null;
        String dateStr = DateUtil.dateToStr(new Date(), DateUtil.DATE_FORMAT.yyyyMMdd);
        //保存图片的地址
        String saveFilePath = filePath + "/" + dateStr + "/";
        File fileDir = new File(saveFilePath);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        try {
            File file3 = new File(saveFilePath + "/"
                    + fileName);
            Boolean b = file3.createNewFile();
            FileOutputStream out = new FileOutputStream(saveFilePath + "/"
                    + fileName);
            out.write(multipartFile.getBytes());
            resultMap.put(fileName,saveFilePath + "/"
                            + fileName);
            out.close();
            return resultMap;

        } catch (Exception e) {
            resultMap.put("error", "1");
            resultMap.put("message", "文件上传错误");
            return resultMap;
        } finally {

        }
    }
}
