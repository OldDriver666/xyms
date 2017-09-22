package com.fise.model.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/**
 * @author bension
 * @email liaoguoshun@qq.com
 * @date 2017-6-2
 * @desc 查询模块参数实体类
 */

public class ModuleQueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @JsonProperty("role_id")
    private Integer roleId;

    @NotNull
    @JsonProperty("company_id")
    private Integer companyId;

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

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
