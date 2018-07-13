package com.eason.api.zb.vo.room;


import java.io.Serializable;

public class RoomResponseVo implements Serializable {

    private Integer planId;          //场次
    private Integer roomId;      //房间ID
    private String roomTitle;  //	房间标题
    private Integer onlineUser;  //房间当前在线用户
    private Integer machineUser;  //房间用户
    private String roomBackgroundImg; //房间背景图片
    private Integer userId;         //主播用户ID
    private String zbNickname;    //主播昵称
    private Integer zbLevel;   //主播等级
    private String zbHeadImg;  //主播头像
    private String zbSignature;    //主播个性签名
    private String playUrl;    //拉流地址
    private String imUrl;    //聊天室地址

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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

    public String getRoomBackgroundImg() {
        return roomBackgroundImg;
    }

    public void setRoomBackgroundImg(String roomBackgroundImg) {
        this.roomBackgroundImg = roomBackgroundImg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getZbNickname() {
        return zbNickname;
    }

    public void setZbNickname(String zbNickname) {
        this.zbNickname = zbNickname;
    }

    public Integer getZbLevel() {
        return zbLevel;
    }

    public void setZbLevel(Integer zbLevel) {
        this.zbLevel = zbLevel;
    }

    public String getZbHeadImg() {
        return zbHeadImg;
    }

    public void setZbHeadImg(String zbHeadImg) {
        this.zbHeadImg = zbHeadImg;
    }

    public String getZbSignature() {
        return zbSignature;
    }

    public void setZbSignature(String zbSignature) {
        this.zbSignature = zbSignature;
    }
}
