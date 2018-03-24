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

    private String thumbnail;
    @JsonDeserialize(using = CustomJsonDateDeserilizer.class)
    private Date startTime;

    @JsonDeserialize(using = CustomJsonDateDeserilizer.class)
    private Date endTime;

    private String location;

    private String categoryId;

    //region_data.id
    private Integer city;

    //region_data.id
    private Integer country;
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

    /**
     * 展会详情
     */
    @TableField(exist = false)
    private ExhibitionDetail exhibitionDetail;
}