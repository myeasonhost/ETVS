package com.eason.api.user.service.impl;

import com.eason.api.exception.ServiceException;
import com.eason.api.zb.service.FUserService;
import com.eason.api.zb.vo.user.TrySeeResponseVo;
import com.eason.api.zb.vo.user.UserResponseVo;
import org.springframework.stereotype.Component;

@Component("userServiceImpl")
public class FUserServiceImpl implements FUserService {
    @Override
    public TrySeeResponseVo isTrySee(Integer userId, Integer roomId, Boolean isTrySee) throws ServiceException {
        throw new ServiceException();
    }

    @Override
    public String isAttention(Integer userId, Integer channel, String userIds, Boolean isAttention) throws ServiceException {
        throw new ServiceException();
    }

    @Override
    public String isBook(Integer userId, Integer zbId, Boolean isBook) throws ServiceException {
        throw new ServiceException();
    }

    @Override
    public String isBlack(Integer userId, Integer channel, String userIds, Boolean isBlack) throws ServiceException {
        throw new ServiceException();
    }

    @Override
    public UserResponseVo getDetail(Integer tokenUserId, Integer userId) throws ServiceException {
        throw new ServiceException();
    }
}
