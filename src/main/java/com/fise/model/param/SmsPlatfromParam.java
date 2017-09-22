package com.fise.model.param;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

public class SmsPlatfromParam implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @JsonProperty("smsplatfrom_id")
    private Integer id;
    
    @JsonProperty("platfrom_name")
    private String platfromName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatfromName() {
        return platfromName;
    }

    public void setPlatfromName(String platfromName) {
        this.platfromName = platfromName;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
    
}
