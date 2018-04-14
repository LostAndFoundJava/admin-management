package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by xufan on 2018/04/02.
 */
@TableName("tb_homepage_category")
@Data
@EqualsAndHashCode(callSuper = false)
public class HomePageCategory extends MgrBaseModel<HomePageCategory>{

        private String categoryId;
        private String homepageId;
        private Integer isChoice;
}
