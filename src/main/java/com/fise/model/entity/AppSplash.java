package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/**
 * @author 
 */
public class AppSplash implements Serializable {
    private Integer id;

    /**
     * 闪屏页名称
     */
    private String name;

    /**
     * 闪屏页链接
     */
    private String url;

    /**
     * 图片点击动作链接
     */
    @JsonProperty("action_url")
    private String actionUrl;

    /**
     * 显示权重,越大越靠前
     */
    private Integer prority;

    /**
     * 0-弃用 1-启用
     */
    private Integer status;

    /**
     * 显示延时
     */
    private Integer delay;

    /**
     * 开始时间
     */
    private Integer updated;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Integer getPrority() {
        return prority;
    }

    public void setPrority(Integer prority) {
        this.prority = prority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
}