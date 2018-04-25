package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oukingtim.config.CustomJsonDateDeserilizer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@TableName("exhibition")
@Data
@EqualsAndHashCode(callSuper = false)
public class Exhibition extends MgrBaseModel<Exhibition> {

    public Exhibition() {

    }

    private String title;

    private String subtitle;
    private String carousel;
    private String thumbnail;
    @JsonDeserialize(using = CustomJsonDateDeserilizer.class)
    private Date startTime;

    @JsonDeserialize(using = CustomJsonDateDeserilizer.class)
    private Date endTime;

    private String location;

    private String categoryId;

    @TableField(exist = false)
    private String categoryName;

    //region_data.id
    private Integer city;

    //region_data.id
    private Integer country;

    private Integer province;
    /**
     * 人气排行
     */
    private Integer hot;

    /**
     * 标签
     */
    private String tag;

    /**
     * 是否设置轮播图
     */
    private Integer hasCarousel;

    @TableField(exist = false)
    private Integer isChoice;

    /**
     * 展会详情
     */
    @TableField(exist = false)
    private ExhibitionDetail exhibitionDetail;
}