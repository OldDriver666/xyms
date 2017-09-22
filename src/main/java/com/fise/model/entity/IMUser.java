package com.fise.model.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class IMUser implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 1男2女0未知
     */
    private Integer sex;

    /**
     * 用户名
     */
    private String name;

    /**
     * 拼音
     */
    private String domain;

    /**
     * 花名,绰号等
     */
    private String nick;

    /**
     * 密码
     */
    private String password;

    /**
     * 混淆码
     */
    private String salt;

    /**
     * 省份
     */
    private String province;

    /**
     * 市县
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * email
     */
    private String email;

    /**
     * 自定义用户头像
     */
    private String avatar;

    /**
     * 身高
     */
    private Integer height;

    /**
     * 体重
     */
    private Integer weight;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 用户类型:1-win 2-mac 17-IOS 18-android 19-fiseDev
     */
    private Integer type;

    /**
     * 所属部门Id
     */
    private Integer departid;

    /**
     * 1. 试用期 2. 正式 3. 离职 4.实习
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Integer created;

    /**
     * 更新时间
     */
    private Integer updated;

    /**
     * 0关闭勿扰 1开启勿扰
     */
    private Integer pushShieldStatus;

    /**
     * 个性签名
     */
    private String signInfo;

    private String lng;

    private String lat;

    private Integer battery;

    private Integer sq;

    /**
     * 加好友是否需要验证
     */
    private Integer friendNeedAuth;

    /**
     * 登录保护
     */
    private Integer loginSafeSwitch;

    /**
     * 允许通过搜索找到我
     */
    private Integer searchAllow;

    /**
     * 1-在线 0-离线
     */
    private Integer onlineStatus;

    /**
     * 最后一次上线时间
     */
    private Integer lastOnlineTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public Integer getPushShieldStatus() {
        return pushShieldStatus;
    }

    public void setPushShieldStatus(Integer pushShieldStatus) {
        this.pushShieldStatus = pushShieldStatus;
    }

    public String getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(String signInfo) {
        this.signInfo = signInfo;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Integer getSq() {
        return sq;
    }

    public void setSq(Integer sq) {
        this.sq = sq;
    }

    public Integer getFriendNeedAuth() {
        return friendNeedAuth;
    }

    public void setFriendNeedAuth(Integer friendNeedAuth) {
        this.friendNeedAuth = friendNeedAuth;
    }

    public Integer getLoginSafeSwitch() {
        return loginSafeSwitch;
    }

    public void setLoginSafeSwitch(Integer loginSafeSwitch) {
        this.loginSafeSwitch = loginSafeSwitch;
    }

    public Integer getSearchAllow() {
        return searchAllow;
    }

    public void setSearchAllow(Integer searchAllow) {
        this.searchAllow = searchAllow;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Integer getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Integer lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }
}