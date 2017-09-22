package com.fise.model.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

public class DeviceCountParam implements Serializable{
    private static final long serialVersionUid=1L;
    
    @JsonProperty("depart_id")
    @NotNull
    private Integer departid;

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }
    
    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
