package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by xufan on 2018/05/27.
 */
@TableName("advice")
@Data
@EqualsAndHashCode(callSuper = false)
public class AdviceModel extends MgrBaseModel<AdviceModel> {

    private String clientName;
    private String company;
    private String mobileNo;
    private String material;
    private Integer status;
}
