package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <br>创建日期：2018/4/17
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@TableName("visa")
@Data
@EqualsAndHashCode(callSuper = false)
public class VisaModel extends MgrBaseModel<VisaModel>{
    private String country;
    private String continent;
    private String visaInfo;
    private String visaUrl;

    @TableField(exist = false)
    private List<File> fileList;

}
