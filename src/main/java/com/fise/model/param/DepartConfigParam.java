package com.fise.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

public class DepartConfigParam {
	@JsonProperty("config_id")
    private Integer configid;

    /**
     * 所属团体
     */
    @JsonProperty("depart_id")
    private Integer departid;

    /**
     * 设备类型
     */
    @JsonProperty("client_type")
    private Integer clienttype;

	public Integer getConfigid() {
		return configid;
	}

	public void setConfigid(Integer configid) {
		this.configid = configid;
	}

	public Integer getDepartid() {
		return departid;
	}

	public void setDepartid(Integer departid) {
		this.departid = departid;
	}

	public Integer getClienttype() {
		return clienttype;
	}

	public void setClienttype(Integer clienttype) {
		this.clienttype = clienttype;
	}

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
}
