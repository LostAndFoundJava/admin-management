package com.oukingtim.service;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <br>创建日期：2018/4/15
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public interface VisaSignService extends IService<File> {

    public Map<String, String> uploadFile(String filePath, MultipartFile multipartFile);
}
