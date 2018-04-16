package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <br>创建日期：2018/4/15
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class FileMsgModel {
    private String fileName;
    private String filePaah;
}
