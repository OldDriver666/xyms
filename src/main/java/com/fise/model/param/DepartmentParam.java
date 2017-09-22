package com.fise.model.param;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

public class DepartmentParam {
    
    @NotNull
    @JsonProperty("company_id")
    private Integer companyId;
    
    @JsonProperty("depart_id")
    private Integer departId;

    @JsonProperty("depart_name")
    private String departName;
    
    @JsonProperty("parent_id")
    private Integer parentId;

    @JsonProperty("status")
    private Integer status;
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
}
