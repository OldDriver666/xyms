package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/**
 * @author 
 */
public class WiAccountManage implements Serializable {
    private Integer id;

    @JsonProperty("depart_id")
    private Integer departId;

    private String description;

    @JsonProperty("begin_account")
    private String beginAccount;

    @JsonProperty("end_account")
    private String endAccount;

    /**
     * 0-初始 1-已经出厂
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Integer created;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeginAccount() {
        return beginAccount;
    }

    public void setBeginAccount(String beginAccount) {
        this.beginAccount = beginAccount;
    }

    public String getEndAccount() {
        return endAccount;
    }

    public void setEndAccount(String endAccount) {
        this.endAccount = endAccount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
}