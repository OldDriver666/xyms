package com.fise.model.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class WiPermission implements Serializable {
    private Integer id;

    /**
     * 0-公有角色 x-指定公司角色
     */
    private Integer companyId;

    private Integer roleId;

    private Integer moduleId;

    /**
     * 1-开启 0-关闭
     */
    private Integer status;

    private Integer insertAuth;

    private Integer updateAuth;

    private Integer queryAuth;

    private Integer updated;

    private Integer created;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
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