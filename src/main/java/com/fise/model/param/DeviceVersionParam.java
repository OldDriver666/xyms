package com.fise.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

public class DeviceVersionParam {
	@JsonProperty("version_id")
    private Integer id;

    /**
     * 公司ID
     */
	@JsonProperty("depart_id")
    private Integer departid;

    /**
     * 设备类型
     */
	@JsonProperty("dev_type")
    private Integer devType;

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartid() {
		return departid;
	}

	public void setDepartid(Integer departid) {
		this.departid = departid;
	}

	public Integer getDevType() {
		return devType;
	}

	public void setDevType(Integer devType) {
		this.devType = devType;
	}

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
	
	
}
