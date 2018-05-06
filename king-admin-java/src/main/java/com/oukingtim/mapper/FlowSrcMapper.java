package com.oukingtim.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oukingtim.domain.FlowSrcModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Mapper
public interface FlowSrcMapper extends BaseMapper<FlowSrcModel> {

    List<FlowSrcModel>selectDataByChannel(@Param("channel")String channel);
}
