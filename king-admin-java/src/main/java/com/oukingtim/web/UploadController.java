package com.oukingtim.web;

import com.oukingtim.domain.SysUser;
import com.oukingtim.service.SysUserService;
import com.oukingtim.web.vm.ResultVM;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by oukingtim
 */
@RestController
@RequestMapping("/sys")
public class UploadController extends BaseController<SysUserService,SysUser>{
    @PostMapping("/upload")
    public ResultVM upload(MultipartHttpServletRequest request){
        List imgName = new ArrayList<String>();

        try {
            Iterator<String> itr = request.getFileNames();
            int i = 0;
            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                byte[] bytes = file.getBytes();

                File tmpFile = new File("/Users/chenjian/Desktop/testImg/a"+i+".jpg");
                tmpFile.createNewFile();
                FileUtils.writeByteArrayToFile(tmpFile,bytes);
                i++;

                imgName.add("a"+i);
            }
        }
        catch (Exception e) {
            return ResultVM.error(500,"upload failed!");
        }

        return ResultVM.ok(imgName);
    }
}