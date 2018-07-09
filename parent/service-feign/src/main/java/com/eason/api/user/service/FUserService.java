package com.eason.api.user.service;

import com.eason.api.service.user.IUserService;
import com.eason.api.service.user.exception.UserServiceException;
import com.eason.api.service.user.vo.code.UserCodeRequestVo;
import com.eason.api.service.user.vo.code.UserCodeResponseVo;
import com.eason.api.service.user.vo.login.LoginRequestVo;
import com.eason.api.service.user.vo.login.LoginResponseVo;
import com.eason.api.service.user.vo.register.RegisterRequestVo;
import com.eason.api.service.user.vo.register.RegisterResponseVo;
import com.eason.api.zb.service.impl.FUserServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-api-user-impl", fallback = FUserServiceImpl.class)
public interface FUserService extends IUserService {
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    RegisterResponseVo register(@RequestBody RegisterRequestVo registerRequestVo) throws UserServiceException;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    LoginResponseVo login(@RequestBody LoginRequestVo loginRequestVo) throws UserServiceException;

    @RequestMapping(value = "/user/reset", method = RequestMethod.POST)
    RegisterResponseVo reset(@RequestBody RegisterRequestVo registerRequestVo) throws UserServiceException;

    @RequestMapping(value = "/user/getValidateCode", method = RequestMethod.POST)
    UserCodeResponseVo getValidateCode(@RequestBody UserCodeRequestVo userCodeRequestVo) throws UserServiceException;
}
