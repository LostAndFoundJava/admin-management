package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.File;
import com.oukingtim.mapper.FileMapper;
import com.oukingtim.service.VisaSignService;
import com.oukingtim.util.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <br>创建日期：2018/4/15
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class VisaSignServiceImpl extends ServiceImpl<FileMapper, File> implements VisaSignService {


    @Override
    public Map<String, String> uploadFile(String filePath, MultipartFile multipartFile) {

        Map<String, String> map = FileUploadUtil.uploadFile(multipartFile, filePath);
        if (map != null &&!map.isEmpty()&& !"0".equals(map.get("error")) && !"1".equals(map.get("error"))) {

            List<File> list = new ArrayList<>();
            File fileMsgModel = new File();
            for (Map.Entry entry : map.entrySet()) {
                fileMsgModel.setFileName((String) entry.getKey());
                fileMsgModel.setFileUrl((String) entry.getValue());
                fileMsgModel.setTypeId("13");
                list.add(fileMsgModel);
            }
            boolean flag = insertBatch(list);
        }
        return map;
    }
}
