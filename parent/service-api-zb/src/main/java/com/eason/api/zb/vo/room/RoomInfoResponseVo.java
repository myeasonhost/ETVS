package com.eason.api.zb.vo.room;


import java.io.Serializable;

public class RoomInfoResponseVo implements Serializable {

    private Integer roomId;      //房间ID
    private String roomTitle;  //	房间标题
    private String roomBgPic;   //房间封面
    private Integer roomStatus;     //直播状态
    private  String result;         //提示信息

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

    public String getRoomBgPic() {
        return roomBgPic;
    }

    public void setRoomBgPic(String roomBgPic) {
        this.roomBgPic = roomBgPic;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
