package com.fise.base;

import java.io.Serializable;

import com.fise.utils.JsonUtil;

public class KeyValueMap  implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String keyName;
    
    private Object keyValue;


    public String getKeyName() {
        return keyName;
    }


    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }


    public Object getKeyValue() {
        return keyValue;
    }


    public void setKeyValue(Object keyValue) {
        this.keyValue = keyValue;
    }


    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
