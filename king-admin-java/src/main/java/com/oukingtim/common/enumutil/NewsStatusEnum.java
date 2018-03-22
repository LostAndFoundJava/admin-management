package com.oukingtim.common.enumutil;

/**
 * <br>创建日期：2018/3/22
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */


public enum NewsStatusEnum {

    NEWS_ONELINE("0", "上线"),
    NEWS_OFFLINE("1", "下线"),
    NEWS_DEL("3", "上线"),
    NEWS_HOT("4", "热点新闻"),
    NEWS_USUAL("5", "普通新闻");


    NewsStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }


    public static NewsStatusEnum getByCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }

        NewsStatusEnum[] codes = NewsStatusEnum.values();
        for (NewsStatusEnum respCode : codes) {
            if (respCode.getCode().equals(code)) {
                return respCode;
            }
        }

        return null;
    }

}


