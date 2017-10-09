package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/**
 * @author 
 */
public class AppAdvert implements Serializable {
    private Integer id;
    
    @JsonProperty("adv_index")
    private String advIndex;

    /**
     * 广告名称
     */
    @JsonProperty("adc_name")
    private String advName;

    /**
     * 图片地址
     */
    @JsonProperty("adv_url")
    private String advUrl;

    /**
     * 0-待审核 1-发布 2-下架
     */
    private Integer status;

    /**
     * 权重
     */
    private Integer prority;

    @JsonProperty("delay_time")
    private Integer delayTime;

    private Integer created;

    private Integer updated;

    /**
     * 描述
     */
    private String description;

    @JsonProperty("inner_type")
    private String innerType;

    @JsonProperty("inner_id")
    private Integer innerId;

    @JsonProperty("inner_name")
    private String innerName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdvIndex() {
        return advIndex;
    }

    public void setAdvIndex(String advIndex) {
        this.advIndex = advIndex;
    }

    public String getAdvName() {
        return advName;
    }

    public void setAdvName(String advName) {
        this.advName = advName;
    }

    public String getAdvUrl() {
        return advUrl;
    }

    public void setAdvUrl(String advUrl) {
        this.advUrl = advUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrority() {
        return prority;
    }

    public void setPrority(Integer prority) {
        this.prority = prority;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInnerType() {
        return innerType;
    }

    public void setInnerType(String innerType) {
        this.innerType = innerType;
    }

    public Integer getInnerId() {
        return innerId;
    }

    public void setInnerId(Integer innerId) {
        this.innerId = innerId;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
}