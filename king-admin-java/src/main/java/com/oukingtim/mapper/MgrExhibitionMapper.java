package com.oukingtim.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oukingtim.domain.Exhibition;

import java.util.List;

/**
 * Created by xufan on 2018/03/19.
 */
public interface MgrExhibitionMapper extends BaseMapper<Exhibition> {
    public List<Exhibition> selectTitleById();
}
