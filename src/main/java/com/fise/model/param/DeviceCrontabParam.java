package com.fise.model.param;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

public class DeviceCrontabParam implements Serializable{
    private static final long serialVersionUID=1L;
    
    @JsonProperty("task_id")
    private Integer taskId;
    
    private String name;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
       
}
