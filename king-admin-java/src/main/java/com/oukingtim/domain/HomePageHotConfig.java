package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonSerialize
    private String extension;

    private String mainCatId;

    private String remark;

    private String title;

    @TableField(exist = false)
    private List<HomePageCategory> homePageCategoryList;

    @TableField(exist = false)
    private List<CategoryExhibition> categoryExhibitionList;

    public HomePageHotConfig(){

    }

}
