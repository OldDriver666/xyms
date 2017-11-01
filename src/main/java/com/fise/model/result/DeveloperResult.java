package com.fise.model.result;

import com.fise.model.entity.WiAdmin;
import com.fise.utils.JsonUtil;

public class DeveloperResult {
	private String account;

	private String nickName;

	private String phone;

	private String email;

	private Integer status;

	private String remarks;

	private String description;

	private String idCard;

	private String cardPhoto;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCardPhoto() {
		return cardPhoto;
	}

	public void setCardPhoto(String cardPhoto) {
		this.cardPhoto = cardPhoto;
	}

	public void init(WiAdmin admin) {
		this.account=admin.getAccount();
		this.nickName=admin.getNickName();
		this.phone=admin.getPhone();
		this.email=admin.getEmail();
		this.status=admin.getStatus();
		this.idCard=admin.getIdCard();
		this.cardPhoto=admin.getCardPhoto();
		this.description=admin.getDescription();
		this.remarks=admin.getRemarks();
	}
	
	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
}
