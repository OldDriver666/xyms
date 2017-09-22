package com.fise.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

public class SystemConfParam {
	@JsonProperty("config_id")
    private Integer configid;

    /**
     * 配置类型
     */
    private String type;
    
    /**
     * 配置类型名称
     */
    private String name;

	public Integer getConfigid() {
		return configid;
	}

	public void setConfigid(Integer configid) {
		this.configid = configid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
