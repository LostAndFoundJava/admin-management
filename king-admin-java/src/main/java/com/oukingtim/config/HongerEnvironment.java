package com.oukingtim.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <br>创建日期：2018/4/15
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Component
@ConfigurationProperties(prefix="fileUploadPath")
public class HongerEnvironment {

    private String filePaht;

}
