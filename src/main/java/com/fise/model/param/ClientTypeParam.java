package com.fise.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

public class ClientTypeParam {
	@JsonProperty("type_id")
    private Integer typeid;

    /**
     * 设备类型
     */
	@JsonProperty("client_type")
    private Integer clienttype;

    /**
     * 设备类型名称
     */
	@JsonProperty("client_name")
    private String clientname;

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Integer getClienttype() {
		return clienttype;
	}

	public void setClienttype(Integer clienttype) {
		this.clienttype = clienttype;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
	
	
}
