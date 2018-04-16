package com.oukingtim.service;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.FlowSrcModel;
import com.oukingtim.mapper.FlowSrcMapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public interface FlowSrcService extends IService<FlowSrcModel>{

    public List<FlowSrcModel> importExcel(String fileName, MultipartFile file);
}
