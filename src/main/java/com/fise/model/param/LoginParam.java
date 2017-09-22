package com.fise.model.param;

import java.io.Serializable;

import com.fise.framework.annotation.MaxLength;
import com.fise.framework.annotation.NotEmpty;
import com.fise.utils.JsonUtil;

/** 
 * @author 大表哥
 * @email xingyuanzuo@gmail.com
 * @date 2016-7-25
 * @desc Login参数对象
 */

public class LoginParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@MaxLength(value = 40)
	private String account;
	
	@NotEmpty
	@MaxLength(value = 50)
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
	
}
