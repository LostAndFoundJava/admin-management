package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <br>创建日期：2018/4/26
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@TableName("channel")
@Data
@EqualsAndHashCode(callSuper = false)
public class ChannelModel extends MgrBaseModel<ChannelModel> {
    private String channelName;
    private String channelCode;
}
