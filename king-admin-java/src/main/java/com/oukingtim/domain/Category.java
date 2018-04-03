package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by xufan on 2018/03/18.
 */
@TableName("category")
@Data
@EqualsAndHashCode(callSuper = false)
public class Category extends MgrBaseModel<Category> {

    private String parentId;
    private String name;
    //父行业id
    private String parent_del;
}
