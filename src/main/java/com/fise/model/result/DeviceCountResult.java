package com.fise.model.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceCountResult implements Serializable{
    
    private static final long serialVersionUid=1L;
    
    @JsonProperty("online_device")
    private Integer onlineDevice;
    
    @JsonProperty("active_device")
    private Integer activeDevice;
    
    @JsonProperty("online_XM")
    private Integer onlineXM;
    
    @JsonProperty("active_XM")
    private Integer activeXM;

    public Integer getOnlineDevice() {
        return onlineDevice;
    }

    public void setOnlineDevice(Integer onlineDevice) {
        this.onlineDevice = onlineDevice;
    }

    public Integer getActiveDevice() {
        return activeDevice;
    }

    public void setActiveDevice(Integer activeDevice) {
        this.activeDevice = activeDevice;
    }

    public Integer getOnlineXM() {
        return onlineXM;
    }

    public void setOnlineXM(Integer onlineXM) {
        this.onlineXM = onlineXM;
    }

    public Integer getActiveXM() {
        return activeXM;
    }

    public void setActiveXM(Integer activeXM) {
        this.activeXM = activeXM;
    }
    
    
}
