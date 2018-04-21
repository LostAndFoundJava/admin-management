package com.oukingtim.util;

import org.apache.commons.lang.StringUtils;

/**
 * <br>创建日期：2018/4/15
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public class BizException  extends RuntimeException{

    private String code = StringUtils.EMPTY;
    private String msg =  StringUtils.EMPTY;

    public  BizException(){}
    public BizException(String msg){
        this.msg = msg;
    }
    public BizException(String code,String message){
        this.code = code;
        this.msg=  message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
