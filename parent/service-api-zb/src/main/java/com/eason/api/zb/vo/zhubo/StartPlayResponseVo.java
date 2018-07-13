package com.eason.api.zb.vo.zhubo;

import java.io.Serializable;

public class StartPlayResponseVo implements Serializable {
    private Integer planId;
    private Integer roomId;
    private String result;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
