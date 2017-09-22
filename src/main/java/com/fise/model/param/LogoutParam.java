package com.fise.model.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/** 
 * @author 大表哥
 * @email xingyuanzuo@gmail.com
 * @date 2016-7-25
 * @desc Logout参数对象
 */

public class LogoutParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("admin_id")
	@NotNull
	private Integer adminId;

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
}
