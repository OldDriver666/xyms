package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 
 */
public class IMSmsPlatfrom implements Serializable {
    @JsonProperty("smsplatfrom_id")
    private Integer id;

    /**
     * 短信平台名称
     */
    @JsonProperty("platfrom_name")
    private String platfromName;

    /**
     * 0-弃用 1-使用
     */
    private Integer status;

    /**
     * 短信平台配置
     */
    private String config;

    private Integer updated;

    private Integer created;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatfromName() {
        return platfromName;
    }

    public void setPlatfromName(String platfromName) {
        this.platfromName = platfromName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }
}