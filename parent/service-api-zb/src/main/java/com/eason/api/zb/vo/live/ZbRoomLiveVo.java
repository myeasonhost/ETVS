package com.eason.api.zb.vo.live;

import java.io.Serializable;
import java.math.BigInteger;

public class ZbRoomLiveVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigInteger id;
    private String roomId;
    private String playUrl;

    public ZbRoomLiveVo() {
    }

    public ZbRoomLiveVo(BigInteger id, String roomId, String playUrl) {
        this.id = id;
        this.roomId = roomId;
        this.playUrl = playUrl;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }
}
