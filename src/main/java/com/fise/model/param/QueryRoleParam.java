package com.fise.model.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fise.utils.JsonUtil;

/**
 * @author 大表哥
 * @email xingyuanzuo@gmail.com
 * @date 2016-7-25
 * @desc Register参数对象
 */

public class QueryRoleParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer company_id;

    @NotNull
    private Integer role_id;
    
    private Integer status;

    private Integer include_all;
    
    private String name;
    
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInclude_all() {
        return include_all;
    }

    public void setInclude_all(Integer include_all) {
        this.include_all = include_all;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
