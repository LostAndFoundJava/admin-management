package com.oukingtim.service;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.Exhibition;

/**
 * Created by xufan on 2018/03/19.
 */
public interface MgrExhibitionService extends IService<Exhibition>{

    Exhibition selectById(Integer id);

}
