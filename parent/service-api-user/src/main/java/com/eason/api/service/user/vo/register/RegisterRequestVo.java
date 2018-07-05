package com.eason.api.service.user.vo.register;

import java.io.Serializable;

public class RegisterRequestVo implements Serializable {
    private String phone;		//手机号
    private String password;	//密码 md5加密
    private String validateCode; //验证码

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
