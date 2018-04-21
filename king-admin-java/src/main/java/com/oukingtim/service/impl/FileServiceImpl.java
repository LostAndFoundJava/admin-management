package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.File;
import com.oukingtim.mapper.FileMapper;
import com.oukingtim.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>创建日期：2018/4/17
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper,File> implements FileService {
    @Autowired
    private FileMapper fileMapper;
    @Override
    public List<File> selectVisaInfo() {
        List<File> list =  fileMapper.selectVisaInfo();
        return list;
    }
}
