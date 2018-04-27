package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oukingtim.config.CustomJsonDateDeserilizer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@TableName("flow_src")
@Data
@EqualsAndHashCode(callSuper = false)
public class FlowSrcModel extends MgrBaseModel<FlowSrcModel> {
    private String src;
    private String uid;
    private String company;
    private String clientName;
    private String address;
    private String qqNo;
    private String email;
    private String mobileNo;
    private String exhibition;
    private String applicationType;
    private String srcType;


    @JsonDeserialize(using = CustomJsonDateDeserilizer.class)
    @TableField(exist = false)
    private Date startTime;


    @JsonDeserialize(using = CustomJsonDateDeserilizer.class)
    @TableField(exist = false)
    private Date endTime;


}
