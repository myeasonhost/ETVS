package com.eason.api.service.user.vo.register;

import java.io.Serializable;

public class RegisterResponseVo implements Serializable {
    private Integer userId;	//用户id
    private String result;	//返回信息

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
