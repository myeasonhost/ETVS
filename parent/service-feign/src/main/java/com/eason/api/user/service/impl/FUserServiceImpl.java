package com.eason.api.user.service.impl;

import com.eason.api.base.vo.model.FileItemModel;
import com.eason.api.service.user.exception.UserServiceException;
import com.eason.api.service.user.vo.code.UserCodeRequestVo;
import com.eason.api.service.user.vo.code.UserCodeResponseVo;
import com.eason.api.service.user.vo.login.LoginRequestVo;
import com.eason.api.service.user.vo.login.LoginResponseVo;
import com.eason.api.service.user.vo.register.RegisterRequestVo;
import com.eason.api.service.user.vo.register.RegisterResponseVo;
import com.eason.api.service.user.vo.user.UserDetailResponseVo;
import com.eason.api.service.user.vo.user.UserInfoRequestVo;
import com.eason.api.service.user.vo.user.UserInfoResponseVo;
import com.eason.api.user.service.FUserService;
import org.springframework.stereotype.Component;

@Component("userServiceImpl")
public class FUserServiceImpl implements FUserService {
    @Override
    public RegisterResponseVo register(RegisterRequestVo registerRequestVo) throws UserServiceException {
        throw new UserServiceException();
    }

    @Override
    public LoginResponseVo login(LoginRequestVo loginRequestVo) throws UserServiceException {
        throw new UserServiceException();
    }

    @Override
    public RegisterResponseVo reset(RegisterRequestVo registerRequestVo) throws UserServiceException {
        throw new UserServiceException();
    }

    @Override
    public UserCodeResponseVo getValidateCode(UserCodeRequestVo userCodeRequestVo) throws UserServiceException {
        throw new UserServiceException();
    }

    @Override
    public UserInfoResponseVo edit(Integer userId, UserInfoRequestVo requestVo) throws UserServiceException {
        throw new UserServiceException();
    }

    @Override
    public String uploadAvatar(Integer userId, FileItemModel fileImg) throws UserServiceException {
        throw new UserServiceException();
    }

    @Override
    public UserDetailResponseVo getDetail(Integer userId) throws UserServiceException {
        throw new UserServiceException();
    }
}
