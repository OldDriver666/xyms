package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/**
 * @author 
 */
public class IMDepartConfig implements Serializable {
    @JsonProperty("config_id")
    private Integer id;

    /**
     * 所属团体
     */
    @JsonProperty("depart_id")
    private Integer departid;

    /**
     * 设备类型
     */
    @JsonProperty("client_type")
    private Integer clienttype;

    /**
     * 默认头像
     */
    private String avatar;

    private Integer created;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    public Integer getClienttype() {
        return clienttype;
    }

    public void setClienttype(Integer clienttype) {
        this.clienttype = clienttype;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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