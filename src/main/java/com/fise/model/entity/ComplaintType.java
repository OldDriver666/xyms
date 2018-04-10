package com.fise.model.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class ComplaintType implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 投诉类型
     */
    private String type;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}