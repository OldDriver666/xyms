package com.fise.model.param;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;



public class DeviceControlParam implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    /**
     * 用户名
     */
    @JsonProperty("device_id")
    private Integer deviceId;
    
    /**
     * 电话号码
     */
    private String mobile;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
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
