package com.eason.api.zb.dao.db.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_info")
public class UserInfoPo implements Serializable{
    private int id;
    private String username;
    private String nickname;
    private String password;
    private BigDecimal deposit;
    private String phone;
    private Date birthday;
    private String signature;
    private String avatar;
    private Short constellation;
    private String location;
    private Short level;
    private Short sex;
    private Short vip;
    private Timestamp exVipTime;
    private Short role;
    private String signage;
    private BigDecimal points;
    private Integer reSignage;
    private Integer isMute;
    private String salt;
    private Byte status;
    private Long exp;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer carId;
    private Timestamp exCarTime;
    private String apiToken;
    private String channel;
    private String os;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 32)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = 64)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 128)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "deposit", nullable = true, precision = 2)
    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "signature", nullable = true, length = 255)
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Basic
    @Column(name = "avatar", nullable = true, length = 255)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "constellation", nullable = true)
    public Short getConstellation() {
        return constellation;
    }

    public void setConstellation(Short constellation) {
        this.constellation = constellation;
    }

    @Basic
    @Column(name = "location", nullable = true, length = 255)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    @Basic
    @Column(name = "sex", nullable = true)
    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "vip", nullable = true)
    public Short getVip() {
        return vip;
    }

    public void setVip(Short vip) {
        this.vip = vip;
    }

    @Basic
    @Column(name = "ex_vip_time", nullable = true)
    public Timestamp getExVipTime() {
        return exVipTime;
    }

    public void setExVipTime(Timestamp exVipTime) {
        this.exVipTime = exVipTime;
    }

    @Basic
    @Column(name = "role", nullable = true)
    public Short getRole() {
        return role;
    }

    public void setRole(Short role) {
        this.role = role;
    }

    @Basic
    @Column(name = "signage", nullable = true, length = 11)
    public String getSignage() {
        return signage;
    }

    public void setSignage(String signage) {
        this.signage = signage;
    }

    @Basic
    @Column(name = "points", nullable = true, precision = 2)
    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    @Basic
    @Column(name = "re_signage", nullable = true)
    public Integer getReSignage() {
        return reSignage;
    }

    public void setReSignage(Integer reSignage) {
        this.reSignage = reSignage;
    }

    @Basic
    @Column(name = "is_mute", nullable = true)
    public Integer getIsMute() {
        return isMute;
    }

    public void setIsMute(Integer isMute) {
        this.isMute = isMute;
    }

    @Basic
    @Column(name = "salt", nullable = true, length = 6)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "exp", nullable = true)
    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "car_id", nullable = true)
    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    @Basic
    @Column(name = "ex_car_time", nullable = true)
    public Timestamp getExCarTime() {
        return exCarTime;
    }

    public void setExCarTime(Timestamp exCarTime) {
        this.exCarTime = exCarTime;
    }

    @Basic
    @Column(name = "api_token", nullable = true, length = 255)
    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    @Basic
    @Column(name = "channel", nullable = true, length = 128)
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Basic
    @Column(name = "os", nullable = true, length = 128)
    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoPo that = (UserInfoPo) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(password, that.password) &&
                Objects.equals(deposit, that.deposit) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(signature, that.signature) &&
                Objects.equals(avatar, that.avatar) &&
                Objects.equals(constellation, that.constellation) &&
                Objects.equals(location, that.location) &&
                Objects.equals(level, that.level) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(vip, that.vip) &&
                Objects.equals(exVipTime, that.exVipTime) &&
                Objects.equals(role, that.role) &&
                Objects.equals(signage, that.signage) &&
                Objects.equals(points, that.points) &&
                Objects.equals(reSignage, that.reSignage) &&
                Objects.equals(isMute, that.isMute) &&
                Objects.equals(salt, that.salt) &&
                Objects.equals(status, that.status) &&
                Objects.equals(exp, that.exp) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(carId, that.carId) &&
                Objects.equals(exCarTime, that.exCarTime) &&
                Objects.equals(apiToken, that.apiToken) &&
                Objects.equals(channel, that.channel) &&
                Objects.equals(os, that.os);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, nickname, password, deposit, phone, birthday, signature, avatar, constellation, location, level, sex, vip, exVipTime, role, signage, points, reSignage, isMute, salt, status, exp, createdAt, updatedAt, carId, exCarTime, apiToken, channel, os);
    }
}
