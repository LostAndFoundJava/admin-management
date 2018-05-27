package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by xufan on 2018/03/18.
 */
@TableName("src")
@Data
@EqualsAndHashCode(callSuper = false)
public class SrcModel extends BaseModel<SrcModel> {

    private String src;

    public SrcModel(){}
    public SrcModel(String src){
        this.src = src;
    }

}
