package com.eason.api.po.user;

import java.sql.Timestamp;

public class UserInfoPo {
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private double deposit;
    private String phone;
    private java.sql.Date birthday;
    private String signature;
    private String avatar;
    private short constellation;
    private String location;
    private short level;
    private short sex;
    private short vip;
    private Timestamp exVipTime;
    private String signage;
    private Double points;
    private Integer reSignage;
    private Integer isMute;
    private String salt;
    private Byte status;
    private Long exp;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
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

    public short getConstellation() {
        return constellation;
    }

    public void setConstellation(short constellation) {
        this.constellation = constellation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public short getSex() {
        return sex;
    }

    public void setSex(short sex) {
        this.sex = sex;
    }

    public short getVip() {
        return vip;
    }

    public void setVip(short vip) {
        this.vip = vip;
    }

    public Timestamp getExVipTime() {
        return exVipTime;
    }

    public void setExVipTime(Timestamp exVipTime) {
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

    public Integer getReSignage() {
        return reSignage;
    }

    public void setReSignage(Integer reSignage) {
        this.reSignage = reSignage;
    }

    public Integer getIsMute() {
        return isMute;
    }

    public void setIsMute(Integer isMute) {
        this.isMute = isMute;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}