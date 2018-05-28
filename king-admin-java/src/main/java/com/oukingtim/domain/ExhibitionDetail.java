package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oukingtim.config.CustomJsonDateDeserilizer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;


@TableName("exhibition_detail")
@Data
@EqualsAndHashCode(callSuper = false)
public class ExhibitionDetail extends MgrBaseModel<ExhibitionDetail> {

    private String exhibitionId;

    private String description;

    private String fileId;

    private String picture;

    private String briefInfo;

    @TableField(exist = false)
    private List<File> files;

    public ExhibitionDetail(String exhibitionId){
        this.exhibitionId = exhibitionId;
    }

    public ExhibitionDetail(){

    }

}