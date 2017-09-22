package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/**
 * @author 
 */
public class IMSmsTemplate implements Serializable {
    private Integer id;

    /**
     * 短信平台ID
     */
    @JsonProperty("platfrom_id")
    private Integer platfromId;

    /**
     * 对应场景号
     */
    private Integer action;

    /**
     * 场景名称
     */
    @JsonProperty("action_name")
    private String actionName;

    /**
     * 短信模板
     */
    @JsonProperty("template_name")
    private String templateName;

    private Integer updated;

    private Integer created;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlatfromId() {
        return platfromId;
    }

    public void setPlatfromId(Integer platfromId) {
        this.platfromId = platfromId;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
}