package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by xufan on 2018/04/02.
 */
@TableName("homepage_hot_config")
@Data
@EqualsAndHashCode(callSuper = false)
public class HomePageHotConfig extends MgrBaseModel<HomePageHotConfig>{

    //存放首页设置数值情况
    private String extension;

    private String mainCatId;

    @TableField(exist = false)
    private List<HomePageCategory> homePageCategory;
}
