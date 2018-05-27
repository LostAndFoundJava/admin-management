package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.FlowSrcLogModel;
import com.oukingtim.domain.SysUser;
import com.oukingtim.mapper.FlowSrcLogMapper;
import com.oukingtim.service.FlowSrcLogService;
import com.oukingtim.util.ShiroUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class FlowSrcLogServiceImpl extends ServiceImpl<FlowSrcLogMapper, FlowSrcLogModel> implements FlowSrcLogService {

    @Override
    public void insertLog(String fileName) {
        SysUser sysUser = ShiroUtils.getUser();
        if (sysUser != null) {
            FlowSrcLogModel t = new FlowSrcLogModel();
            t.setCreateBy(sysUser.getId());
            t.setFileName(fileName);
            t.setCreateTime(new Date());
            t.setDelete(0);
            t.setUpdateTime(new Date());
            super.insert(t);
        }
    }

    @Override
    public List<FlowSrcLogModel> getLogs(String userId) {
        return super.selectList(new EntityWrapper<>(new FlowSrcLogModel(userId)).orderBy("create_time",false));
    }

}
