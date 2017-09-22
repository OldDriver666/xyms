package com.fise.model.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class WiAdmin implements Serializable {
    private Integer id;

    /**
     * 用户名
     */
    private String account;

    /**
     * 混淆码
     */
    private String salt;

    /**
     * 密码
     */
    private String password;

    /**
     * 管理员昵称
     */
    private String nickName;

    /**
     * 用户角色
     */
    private Integer roleId;

    /**
     * 用户所属公司
     */
    private Integer companyId;

    private Integer departId;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 联系人电子邮箱
     */
    private String email;

    /**
     * accessToken
     */
    private String accessToken;

    /**
     * 0-不可用，1-可用
     */
    private Integer status;

    /**
     * 最后一次登录时间´
     */
    private Integer lastLogin;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Integer lastLogin) {
        this.lastLogin = lastLogin;
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
}