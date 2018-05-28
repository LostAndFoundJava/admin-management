package com.oukingtim.service;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.AdviceModel;

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public interface AdviceService extends IService<AdviceModel> {
    AdviceModel deal(String id);
}
