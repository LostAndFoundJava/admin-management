package com.oukingtim.domain;

import java.util.Date;

public class ClickCount {
    private String id;
    private Integer delete;
    private Date createTime;
    private Date updateTime;
    //关联点击id（展会，新闻）
    private String clickedId;
    //访问次数
    private Integer count;
    //被点击类型
    private Integer clickType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getClickedId() {
        return clickedId;
    }

    public void setClickedId(String clickedId) {
        this.clickedId = clickedId;
    }

    public Integer getClickType() {
        return clickType;
    }

    public void setClickType(Integer clickType) {
        this.clickType = clickType;
    }
}