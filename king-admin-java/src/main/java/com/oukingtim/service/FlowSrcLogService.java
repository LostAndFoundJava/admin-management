package com.oukingtim.service;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.FlowSrcLogModel;

import java.util.List;

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public interface FlowSrcLogService extends IService<FlowSrcLogModel>{

    void insertLog(String fileName);

    List<FlowSrcLogModel> getLogs(String id);
}
