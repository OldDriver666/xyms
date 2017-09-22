package com.fise.model.param;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.framework.annotation.MaxLength;
import com.fise.framework.annotation.NotEmpty;
import com.fise.utils.JsonUtil;

public class AdminInsert {

    @NotNull
    @JsonProperty("admin_id")
    private Integer adminId;

    @NotEmpty
    @MaxLength(value = 40)
    private String account;

    @NotEmpty
    @MaxLength(value = 50)
    private String password;

    private Integer status;

    @JsonProperty("nick_name")
    private String nickName;

    @NotNull
    @JsonProperty("role_id")
    private Integer roleId;

    @NotNull
    @JsonProperty("company_id")
    private Integer companyId;

    @JsonProperty("depart_id")
    private Integer departId;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer compayId) {
        this.companyId = compayId;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    private String phone;

    private String email;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String toString() {
        return JsonUtil.toJson(this);
    }

}
