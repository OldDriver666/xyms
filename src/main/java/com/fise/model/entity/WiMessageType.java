package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 
 */
public class WiMessageType implements Serializable {
    /**
     * 消息类型
     */
    @JsonProperty("msg_type")
    private Integer msgType;

    /**
     * 消息名称
     */
    @JsonProperty("msg_name")
    private String msgName;

    private static final long serialVersionUID = 1L;

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsgName() {
        return msgName;
    }

    public void setMsgName(String msgName) {
        this.msgName = msgName;
    }
}