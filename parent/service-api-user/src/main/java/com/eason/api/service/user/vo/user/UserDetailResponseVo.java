package com.eason.api.service.user.vo.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserDetailResponseVo implements Serializable {
    private Integer userId;	//用户id

    private String username;

    private String nickname;

    private Double deposit;

    private String phone;

    private Date birthday;

    private String signature;

    private String avatar;

    private String location;

    private Short level;

    private Short sex;

    private Short vip;

    private Date exVipTime;

    private String signage;

    private Double points;

    private Byte status;

    private Date createdAt;

    private Date updatedAt;

    private String channel;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public Short getVip() {
        return vip;
    }

    public void setVip(Short vip) {
        this.vip = vip;
    }

    public Date getExVipTime() {
        return exVipTime;
    }

    public void setExVipTime(Date exVipTime) {
        this.exVipTime = exVipTime;
    }

    public String getSignage() {
        return signage;
    }

    public void setSignage(String signage) {
        this.signage = signage;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
