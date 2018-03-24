package com.oukingtim.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xufan on 2018/03/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class MgrBaseModel<T extends MgrBaseModel> implements Serializable {
    private static final long serialVersionUID = 3151489830021157829L;
    private String id;
    private Date createTime;
    private Date updateTime;
    private Integer delete;

    protected Serializable pkVal() {
        // TODO Auto-generated method stub
        return id;
    }
}