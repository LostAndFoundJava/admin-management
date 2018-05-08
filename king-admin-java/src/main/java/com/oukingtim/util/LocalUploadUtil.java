package com.oukingtim.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chenjian on 2018/5/8.
 */
public class LocalUploadUtil {
    public static boolean localUploadFile(String basePath, String filePath, String filename, InputStream input){
        try{
            File file = new File(basePath+filePath+"/"+filename);
            byte[] bytes = IOUtils.toByteArray(input);
            FileUtils.writeByteArrayToFile(file,bytes);
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
