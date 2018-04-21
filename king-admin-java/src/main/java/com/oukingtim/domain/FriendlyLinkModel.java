package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <br>创建日期：2018/4/21
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@TableName("link")
@Data
@EqualsAndHashCode(callSuper = false)
public class FriendlyLinkModel extends MgrBaseModel<FriendlyLinkModel> implements Serializable{
    private String linkName;
    private String linkUrl;
    private String picName;
    private String picUrl;
}
