package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/**
 * @author 
 */
public class IMSuggest implements Serializable {
    @JsonProperty("suggest_id")
    private Integer id;

    @JsonProperty("user_id")
    private Integer userId;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 记录状态 0 :初始正常 1:已经回复
     */
    private Integer status;

    /**
     * 意见内容
     */
    private String suggestion;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 创建时间´
     */
    private Integer created;

    /**
     * 更新时间´
     */
    private Integer updated;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
}