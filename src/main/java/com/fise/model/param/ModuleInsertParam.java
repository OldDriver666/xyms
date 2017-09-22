package com.fise.model.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.framework.annotation.NotEmpty;
import com.fise.utils.JsonUtil;

/**
 * @author bension
 * @email liaoguoshun@qq.com
 * @date 2017-6-2
 * @desc 新增模块参数实体类
 */

public class ModuleInsertParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer module_id;

    @NotEmpty
    private String name;

    private String description;

    @NotNull
    private Integer priority;

    @NotEmpty
    private String sn;
    @NotNull
    private Integer company_id;
    private Integer module_type;
    private Integer status;

    private String url;

    @NotNull
    @JsonProperty("parent_id")
    private Integer parentId;

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getModule_type() {
        return module_type;
    }

    public void setModule_type(Integer module_type) {
        this.module_type = module_type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getModule_id() {
        return module_id;
    }

    public void setModule_id(Integer module_id) {
        this.module_id = module_id;
    }
    
    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
