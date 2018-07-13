package com.eason.api.zb.service.impl;

import com.eason.api.exception.ServiceException;
import com.eason.api.zb.service.FZhuboService;
import com.eason.api.zb.vo.user.UserLevelRankResponseVo;
import com.eason.api.zb.vo.user.UserResponseVo;
import com.eason.api.zb.vo.zhubo.ReadyPlayResponseVo;
import com.eason.api.zb.vo.zhubo.StartPlayRequestVo;
import com.eason.api.zb.vo.zhubo.StartPlayResponseVo;
import com.eason.api.zb.vo.zhubo.ZhuboResponseVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("zhuboServiceImpl")
public class FZhuboServiceImpl implements FZhuboService {
    @Override
    public StartPlayResponseVo startPlay(Integer userId, StartPlayRequestVo startPlayRequestVo) throws ServiceException {
        throw new ServiceException();
    }
}
