package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@TableName("about_us")
@Data
@EqualsAndHashCode(callSuper = false)
public class AboutUsModel extends MgrBaseModel<AboutUsModel> {
    private String company;
    private String conductUs;
    private String qqNo;
    private String weChat;
    private String weibo;
    private String address;
    private String telNO;
    private String mobileNo;
    private String faxCode;
}
