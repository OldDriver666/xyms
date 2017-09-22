package com.fise.model.param;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.framework.annotation.MaxLength;
import com.fise.utils.JsonUtil;

public class AdminQuery {

    @NotNull
    @JsonProperty("admin_id")
    private Integer adminId;

    @JsonProperty("company_id")
    private Integer companyId;

    @MaxLength(value = 40)
    private String account;

    @JsonProperty("role_id")
    private Integer roleId;

    @JsonProperty("depart_id")
    private Integer departId;

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer ruleId) {
        this.roleId = ruleId;
    }

    public String toString() {
        return JsonUtil.toJson(this);
    }
}
