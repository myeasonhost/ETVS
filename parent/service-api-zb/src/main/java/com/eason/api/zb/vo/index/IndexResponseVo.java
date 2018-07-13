package com.eason.api.zb.vo.index;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class IndexResponseVo implements Serializable {

    private Integer roomId;     //房间ID
    private Integer roomPlanId;  //场次ID
    private Integer userId;     //主播Id（用户Id）
    private String username;     //主播昵称
    private String nickname;     //主播昵称
    private String avatar;       //主播头像
    private String roomTitle;     // 房间标题
    private Integer onlineUser ;   //真实在线用户
    private Integer machineUser;     //机器用户
    private Integer viewCount;          //浏览次数
    private Integer watchCount;          //观看次数
    private String roomBackgroundImg;     // 房间背景图片
    private Integer roomStatus;     // 直播状态： 1=直播中（2=回放中）
    private Timestamp startTime;     //房间开播时间

    public IndexResponseVo() {
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomPlanId() {
        return roomPlanId;
    }

    public void setRoomPlanId(Integer roomPlanId) {
        this.roomPlanId = roomPlanId;
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

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public Integer getOnlineUser() {
        return onlineUser;
    }

    public void setOnlineUser(Integer onlineUser) {
        this.onlineUser = onlineUser;
    }

    public Integer getMachineUser() {
        return machineUser;
    }

    public void setMachineUser(Integer machineUser) {
        this.machineUser = machineUser;
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

    public String getRoomBackgroundImg() {
        return roomBackgroundImg;
    }

    public void setRoomBackgroundImg(String roomBackgroundImg) {
        this.roomBackgroundImg = roomBackgroundImg;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
}
