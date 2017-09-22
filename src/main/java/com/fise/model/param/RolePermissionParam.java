package com.fise.model.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/**
 * @author bension
 * @email liaoguoshun@qq.com
 * @date 2017-6-13
 * @desc 权限新增增删改查接口参数
 */

public class RolePermissionParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @JsonProperty("key_id")
    private Integer permissionId;
    
    @JsonProperty("status")
    private Integer status;

    @JsonProperty("insert_auth")
    private Integer insertAuth;

    @JsonProperty("update_auth")
    private Integer updateAuth;

    @JsonProperty("query_auth")
    private Integer queryAuth;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInsertAuth() {
        return insertAuth;
    }

    public void setInsertAuth(Integer insertAuth) {
        this.insertAuth = insertAuth;
    }

    public Integer getUpdateAuth() {
        return updateAuth;
    }

    public void setUpdateAuth(Integer updateAuth) {
        this.updateAuth = updateAuth;
    }

    public Integer getQueryAuth() {
        return queryAuth;
    }

    public void setQueryAuth(Integer queryAuth) {
        this.queryAuth = queryAuth;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
