package com.eason.api.zb.dao.mongo.po;

import com.eason.api.zb.model.GeneratedValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


@Document(collection = "zb_t_room_plan")
public class ZbTRoomPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Id
    private int planId;

    private Date closeTime;

    private Integer giftCount;

    private Integer incomeAmount;

    private Integer machineUser;

    private Integer onlineUser;

    private Date openTime;

    @Indexed(unique = true)
    private Integer roomId;

    private Integer room_No1;

    private Integer orderField;

    private String roomTitle;

    private Integer roomStatus;      //房间状态

    private String roomBgPic;           //房间背景图

    private Integer viewCount;

    private Integer watchCount;

    @Indexed(unique = true)
    private Integer userId;                //用户ID

    private String username;

    private String nickname;

    private String avatar;

    private String  signature;       //主播的用户签名

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(Integer giftCount) {
        this.giftCount = giftCount;
    }

    public Integer getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(Integer incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public Integer getMachineUser() {
        return machineUser;
    }

    public void setMachineUser(Integer machineUser) {
        this.machineUser = machineUser;
    }

    public Integer getOnlineUser() {
        return onlineUser;
    }

    public void setOnlineUser(Integer onlineUser) {
        this.onlineUser = onlineUser;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoom_No1() {
        return room_No1;
    }

    public void setRoom_No1(Integer room_No1) {
        this.room_No1 = room_No1;
    }

    public Integer getOrderField() {
        return orderField;
    }

    public void setOrderField(Integer orderField) {
        this.orderField = orderField;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomBgPic() {
        return roomBgPic;
    }

    public void setRoomBgPic(String roomBgPic) {
        this.roomBgPic = roomBgPic;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(Integer watchCount) {
        this.watchCount = watchCount;
    }

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}