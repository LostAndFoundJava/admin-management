package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by xufan on 2018/03/19.
 */
@TableName("file")
@Data
@EqualsAndHashCode(callSuper = false)
public class File extends MgrBaseModel<File> {

    private String fileUrl;

    private String fileName;

    private Integer fileType;

}
