package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@TableName("exhibition")
@Data
@EqualsAndHashCode(callSuper = false)
public class Exhibition extends MgrBaseModel<Exhibition> {

    private String title;

    private String thumbnail;

    private Date startTime;

    private Date endTime;

    private String location;

    private Integer categoryId;

    private Integer city;

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