package com.fise.model.param;

import java.io.Serializable;

import com.fise.utils.JsonUtil;



public class DeviceControlParam implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    /**
     * 用户名
     */
    private String name;
    
    /**
     * 电话号码
     */
    private String mobile;
    


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
    
}
