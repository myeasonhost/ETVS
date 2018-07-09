package com.eason.api.zb.service.impl;

import com.eason.api.exception.ServiceException;
import com.eason.api.zb.service.FLiveUrlService;
import com.eason.api.zb.vo.live.ZbRoomLiveVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("liveUrlServiceImpl")
public class FLiveUrlServiceImpl implements FLiveUrlService {
    @Override
    public String getPushUrl(String roomId) throws ServiceException {
        throw new ServiceException();
    }

    @Override
    public String getPlayUrl(String roomId) throws ServiceException {
        throw new ServiceException();
    }

    @Override
    public List<ZbRoomLiveVo> getPlayUrlList() throws ServiceException {
        throw new ServiceException();
    }

    @Override
    public String removeLiveInfo(String roomId) throws ServiceException {
        throw new ServiceException();
    }
}
