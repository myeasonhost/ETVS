package com.eason.api.zb.service.impl;

import com.eason.api.exception.ServiceException;
import com.eason.api.zb.service.FPlatformService;
import com.eason.api.zb.vo.platform.IMResponseVo;
import com.eason.api.zb.vo.platform.MediaResponseVo;
import org.springframework.stereotype.Component;

@Component("platformServiceImpl")
public class FPlatformServiceImpl implements FPlatformService {
    @Override
    public MediaResponseVo getMedia(Integer zbId, String token) throws ServiceException {
       throw new ServiceException();
    }

    @Override
    public IMResponseVo getIM(Integer zbId, String token) throws ServiceException {
       throw new ServiceException();
    }
}
