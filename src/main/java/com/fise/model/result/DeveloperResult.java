package com.fise.model.result;

import com.fise.model.entity.WiAdmin;
import com.fise.utils.JsonUtil;

public class DeveloperResult {
	private Integer developerId;
	
	private String account;

	private String nickName;

	private String phone;

	private String email;

	private Integer status;
	
	private Integer created;
	
	private Integer creatorId;
	
	private String accessToken;

	private String remarks;

	private String description;

	private String idCard;

	private String cardPhoto;

	public Integer getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(Integer developerId) {
		this.developerId = developerId;
	}

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

	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void init(WiAdmin admin) {
		this.developerId=admin.getId();
		this.account=admin.getAccount();
		this.nickName=admin.getNickName();
		this.phone=admin.getPhone();
		this.email=admin.getEmail();
		this.status=admin.getStatus();
		this.idCard=admin.getIdCard();
		this.cardPhoto=admin.getCardPhoto();
		this.description=admin.getDescription();
		this.remarks=admin.getRemarks();
		this.accessToken=admin.getAccessToken();
		this.created=admin.getCreated();
		this.creatorId=admin.getCreatorId();
	}
	
	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
}
