package com.fise.model.param;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.model.entity.AppChannelList;
import com.fise.utils.JsonUtil;

public class AppCheckUpParam {
	
	@NotNull
	@JsonProperty("app_id")
	private Integer appId;
	
	private Integer status;
	
	@JsonProperty("channel_id")
	private Integer channelId;
	
    @JsonProperty("channel_list")
    private List<AppChannelList> channelList = new ArrayList<AppChannelList>();
	
	private Integer updated;
	
	private String remarks;

	public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public List<AppChannelList> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<AppChannelList> channelList) {
		this.channelList = channelList;
	}

	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
}
