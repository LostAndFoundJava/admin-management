package com.oukingtim.domain;


import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("region_data")
@Data
@EqualsAndHashCode(callSuper = false)
public class RegionData extends MgrBaseModel<RegionData> {
    private Integer pid;

    private String path;

    private Integer level;

    private String name;

    private String nameEn;

    private String namePinyin;

    private String code;

}