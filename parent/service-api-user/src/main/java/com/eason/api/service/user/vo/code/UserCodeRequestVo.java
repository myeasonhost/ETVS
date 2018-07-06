package com.eason.api.service.user.vo.code;

import java.io.Serializable;

public class UserCodeRequestVo implements Serializable {
    private Integer codeType;	//验证码类型1为注册 2为忘记密码
    private String phone;		//手机号

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }
}
