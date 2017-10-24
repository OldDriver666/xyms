package com.fise.model.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class IMRelationShip implements Serializable {
    private Integer id;

    /**
     * 用户A的id
     */
    private Integer smallid;

    /**
     * 用户B的id
     */
    private Integer bigid;

    /**
     * 0-非好友，1-好友，2-雨友
     */
    private Integer status;

    /**
     * small权限值
     */
    private Integer smallpriority;

    /**
     * big权限值
     */
    private Integer bigpriority;

    /**
     * 创建时间
     */
    private Integer created;

    /**
     * 更新时间
     */
    private Integer updated;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSmallid() {
        return smallid;
    }

    public void setSmallid(Integer smallid) {
        this.smallid = smallid;
    }

    public Integer getBigid() {
        return bigid;
    }

    public void setBigid(Integer bigid) {
        this.bigid = bigid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSmallpriority() {
        return smallpriority;
    }

    public void setSmallpriority(Integer smallpriority) {
        this.smallpriority = smallpriority;
    }

    public Integer getBigpriority() {
        return bigpriority;
    }

    public void setBigpriority(Integer bigpriority) {
        this.bigpriority = bigpriority;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }
}