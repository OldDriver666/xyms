package com.fise.model.result;

import java.io.Serializable;

public class ModulePermissResult implements Serializable{
    
    private static final long serialVersionUID=1L;
    private Integer module_id;
    private String module_name;
    private String url;
    private Integer module_type;
    private Integer priority;
    private Integer parent_id;
    private Integer status;

    private Integer permiss_id;
    private Integer insert_auth;
    private Integer update_auth;
    private Integer query_auth;

    public String getModule_name() {
        return module_name;
    }
    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getModule_type() {
        return module_type;
    }
    public void setModule_type(Integer module_type) {
        this.module_type = module_type;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public Integer getParent_id() {
        return parent_id;
    }
    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }
    public Integer getPermiss_id() {
        return permiss_id;
    }
    public void setPermiss_id(Integer permiss_id) {
        this.permiss_id = permiss_id;
    }
    public Integer getInsert_auth() {
        return insert_auth;
    }
    public void setInsert_auth(Integer insert_auth) {
        this.insert_auth = insert_auth;
    }
    public Integer getUpdate_auth() {
        return update_auth;
    }
    public void setUpdate_auth(Integer update_auth) {
        this.update_auth = update_auth;
    }
    
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getQuery_auth() {
        return query_auth;
    }
    public void setQuery_auth(Integer query_auth) {
        this.query_auth = query_auth;
    }
    public Integer getModule_id() {
        return module_id;
    }
    public void setModule_id(Integer module_id) {
        this.module_id = module_id;
    }
}
