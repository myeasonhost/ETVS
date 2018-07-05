package com.eason.api.service.user.vo.code;

import java.io.Serializable;

public class UserCodeResponseVo implements Serializable {
    private String code;	//用户id
    private String result;	//返回信息

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
