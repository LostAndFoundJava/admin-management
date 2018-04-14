package com.oukingtim.service;

import com.baomidou.mybatisplus.service.IService;
import com.oukingtim.domain.Exhibition;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xufan on 2018/03/19.
 */
public interface MgrExhibitionService extends IService<Exhibition>{

    @Override
    Exhibition selectById(Serializable id);


    @Override
    boolean updateById(Exhibition entity);


    List<Exhibition> getExhibitionsByCategoryId(List<String> categoryIds);
}
