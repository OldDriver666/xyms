package com.fise.model.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class IMMark implements Serializable {
    private Integer id;

    /**
     * 用户ID
     */
    private Integer fromUser;

    /**
     * 用户ID
     */
    private Integer destUser;

    /**
     * 0-备注 1-标签
     */
    private Integer markType;

    /**
     * 名称
     */
    private String markName;

    /**
     * 0-删除 1-正常
     */
    private Integer status;

    private Integer updated;

    private Integer created;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromUser() {
        return fromUser;
    }

    public void setFromUser(Integer fromUser) {
        this.fromUser = fromUser;
    }

    public Integer getDestUser() {
        return destUser;
    }

    public void setDestUser(Integer destUser) {
        this.destUser = destUser;
    }

    public Integer getMarkType() {
        return markType;
    }

    public void setMarkType(Integer markType) {
        this.markType = markType;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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