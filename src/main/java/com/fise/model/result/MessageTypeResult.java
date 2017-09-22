package com.fise.model.result;

import java.io.Serializable;

public class MessageTypeResult implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    private Integer keyName;
    
    private Integer keyValue;

    public Integer getKeyName() {
        return keyName;
    }

    public void setKeyName(Integer keyName) {
        this.keyName = keyName;
    }

    public Integer getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(Integer keyValue) {
        this.keyValue = keyValue;
    }
    
    
}
