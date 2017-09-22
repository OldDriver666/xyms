package com.fise.model.param;

import java.io.Serializable;

import com.fise.utils.JsonUtil;

public class SmsTemplateParam implements Serializable{
    
    private static final long SerialVersionUID=1L;
    
    private Integer action;

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
    
}
