package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by xufan on 2018/04/02.
 */
@TableName("tb_category_exhibition")
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryExhibition extends MgrBaseModel<CategoryExhibition>{

    private String categoryId;
    private String exhibitionId;
    private Integer isHot;
    private Integer isCarousel;
    private Integer isChoice;
    private String homepageId;

    @TableField(exist = false)
    private Exhibition exhibition;

    public CategoryExhibition(String categoryId){
        this.categoryId = categoryId;
    }
}
