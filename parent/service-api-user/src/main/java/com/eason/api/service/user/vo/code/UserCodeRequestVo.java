package com.eason.api.service.user.vo.code;

import java.io.Serializable;

public class UserCodeRequestVo implements Serializable {
    private String phone;		//手机号

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
