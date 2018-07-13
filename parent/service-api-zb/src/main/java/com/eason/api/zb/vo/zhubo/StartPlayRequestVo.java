package com.eason.api.zb.vo.zhubo;

import java.io.Serializable;

public class StartPlayRequestVo implements Serializable {
    private String roomTitle;      //房间标题
    private String roomBackgroundImg;  //房间图片

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getRoomBackgroundImg() {
        return roomBackgroundImg;
    }

    public void setRoomBackgroundImg(String roomBackgroundImg) {
        this.roomBackgroundImg = roomBackgroundImg;
    }
}
