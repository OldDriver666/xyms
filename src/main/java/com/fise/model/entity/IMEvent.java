package com.fise.model.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class IMEvent implements Serializable {
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 对应IM.BaseDefine.EventKey
     */
    private Integer eventKey;

    /**
     * 纬度
     */
    private String locationX;

    /**
     * 经度
     */
    private String locationY;

    /**
     * 0-GPS 1-基站 2-WIFI
     */
    private Integer locationFrom;

    /**
     * 电量
     */
    private Integer battery;

    /**
     * 信号
     */
    private Integer sq;

    /**
     * 0-广播 1-持久 2-实时
     */
    private Integer eventLevel;

    /**
     * 重复时间字符串
     */
    private String param;

    private Integer updated;

    private Integer created;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventKey() {
        return eventKey;
    }

    public void setEventKey(Integer eventKey) {
        this.eventKey = eventKey;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public Integer getLocationFrom() {
        return locationFrom;
    }

    public void setLocationFrom(Integer locationFrom) {
        this.locationFrom = locationFrom;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Integer getSq() {
        return sq;
    }

    public void setSq(Integer sq) {
        this.sq = sq;
    }

    public Integer getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(Integer eventLevel) {
        this.eventLevel = eventLevel;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
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
}