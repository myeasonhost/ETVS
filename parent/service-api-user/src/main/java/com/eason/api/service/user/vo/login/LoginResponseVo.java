package com.eason.api.service.user.vo.login;

import java.io.Serializable;

public class LoginResponseVo implements Serializable {

    private Integer userId;         //用户ID
    private String username;        //登陆用户名
    private String nickname;        //用户昵称
    private String avatar;          //用户头像
    private String  token;          //用户token

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
