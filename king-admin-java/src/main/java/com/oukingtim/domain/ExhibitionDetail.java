package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;


@TableName("exhibition_detail")
@Data
@EqualsAndHashCode(callSuper = false)
public class ExhibitionDetail extends MgrBaseModel<ExhibitionDetail> {

    private String exhibitionId;

    private Date createTime;

    private Date updateTime;

    private String description;

    private String fileId;

    private String picture;

    @TableField(exist = false)
    private List<File> files;
}