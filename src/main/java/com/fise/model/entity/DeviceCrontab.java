package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/**
 * @author 
 */
public class DeviceCrontab implements Serializable {
    @JsonProperty("task_id")
    private Integer taskId;

    /**
     * 设备号
     */
    @JsonProperty("device_id")
    private Integer deviceId;

    /**
     * 任务类型 0-上课记录 1-关爱记录
     */
    @JsonProperty("task_type")
    private Integer taskType;

    /**
     * 任务标题
     */
    @JsonProperty("task_name")
    private String taskName;

    /**
     * 任务参数
     */
    @JsonProperty("task_param")
    private String taskParam;

    /**
     * 开始时间
     */
    @JsonProperty("begin_time")
    private String beginTime;

    /**
     * 结束时间
     */
    @JsonProperty("end_time")
    private String endTime;

    /**
     * 响铃模式 0-关闭 1-开启 3-删除
     */
    private Integer status;

    /**
     * 重复模式0-关闭 1-开启
     */
    @JsonProperty("repeat_mode")
    private Integer repeatMode;

    /**
     * 重复时间字符串
     */
    @JsonProperty("repeat_value")
    private String repeatValue;

    private Integer updated;

    private Integer created;

    private static final long serialVersionUID = 1L;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskParam() {
        return taskParam;
    }

    public void setTaskParam(String taskParam) {
        this.taskParam = taskParam;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRepeatMode() {
        return repeatMode;
    }

    public void setRepeatMode(Integer repeatMode) {
        this.repeatMode = repeatMode;
    }

    public String getRepeatValue() {
        return repeatValue;
    }

    public void setRepeatValue(String repeatValue) {
        this.repeatValue = repeatValue;
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

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
    
}