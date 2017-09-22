package com.fise.model.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class WiModule implements Serializable {
    private Integer id;

    private String name;

    /**
     * 0-管理 1-查询
     */
    private Integer moduleType;

    /**
     * 所属公司0为公有菜单
     */
    private Integer belongCompany;

    private String description;

    private Integer priority;

    /**
     * 0-弃用 1-启用
     */
    private Integer status;

    private String sn;

    private String url;

    private Integer parentId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public Integer getBelongCompany() {
        return belongCompany;
    }

    public void setBelongCompany(Integer belongCompany) {
        this.belongCompany = belongCompany;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}