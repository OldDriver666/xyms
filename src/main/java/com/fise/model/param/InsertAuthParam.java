package com.fise.model.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;


public class InsertAuthParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @JsonProperty("role_id")
    private Integer roleId;
    
    @NotNull
    @JsonProperty("module_id")
    private Integer moduleId;

    @NotNull
    @JsonProperty("company_id")
    private Integer companyId;


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


    public Integer getCompanyId() {
        return companyId;
    }


    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }


    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
