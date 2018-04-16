package com.oukingtim.domain;

/*
* 查询洲:level=1 pid=0,获取洲id
*
* 通过洲id作为pid 查询下属国家id,再通过国家id作为pid查询城市id
*
* */

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("region_data")
@Data
@EqualsAndHashCode(callSuper = false)
public class RegionData{
    private Integer id;

    private Integer pid;

    private String path;

    private Integer level;

    private String name;

    private String nameEn;

    private String namePinyin;

    private String code;

    public RegionData() {
    }

    public RegionData(Integer level) {
        this.level = level;
    }

}