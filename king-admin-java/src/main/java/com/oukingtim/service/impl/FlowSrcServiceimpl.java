package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.FlowSrcModel;
import com.oukingtim.mapper.FlowSrcMapper;
import com.oukingtim.service.FlowSrcService;
import com.oukingtim.util.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class FlowSrcServiceimpl extends ServiceImpl<FlowSrcMapper, FlowSrcModel> implements FlowSrcService {

    @Autowired
    private ReadExcel readExcel;
    @Override
    public List<FlowSrcModel> importExcel(String fileName, MultipartFile file) {
        List<FlowSrcModel> list = readExcel.gotExcelInfo(fileName, file);
        return list;
    }
}
