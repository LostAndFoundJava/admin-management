package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@TableName("flow_src_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class FlowSrcLogModel extends MgrBaseModel<FlowSrcLogModel> {

    private String fileName;
    private String createBy;


    public FlowSrcLogModel() {
    }

    public FlowSrcLogModel(String createBy) {
        this.createBy = createBy;
    }
}
