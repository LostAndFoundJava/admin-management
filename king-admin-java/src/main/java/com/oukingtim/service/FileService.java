package com.oukingtim.service;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.File;

import java.util.List;

/**
 * <br>创建日期：2018/4/17
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public interface FileService extends IService<File> {

    public List<File> selectVisaInfo ();

}
