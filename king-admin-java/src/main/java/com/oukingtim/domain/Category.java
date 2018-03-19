package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by xufan on 2018/03/18.
 */
@TableName("category")
@Data
@EqualsAndHashCode(callSuper = false)
public class Category extends MgrBaseModel<Category> {

    private Integer parentId;
    private String name;

}
