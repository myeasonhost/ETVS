package com.eason.api.zb.service.impl;

import com.eason.api.exception.ServiceException;
import com.eason.api.zb.model.PageModel;
import com.eason.api.zb.service.FIndexService;
import com.eason.api.zb.vo.index.BannerResponseVo;
import com.eason.api.zb.vo.index.IndexResponseVo;
import com.eason.api.zb.vo.index.MsgNotificationResponseVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("indexServiceImpl")
public class FIndexServiceImpl implements FIndexService {
    @Override
    public PageModel<IndexResponseVo> getIndexList(String category, Integer position, Integer pageSize) throws ServiceException {
       throw new ServiceException();
    }

    @Override
    public List<BannerResponseVo> getBannerList(String category) throws ServiceException {
       throw new ServiceException();
    }

    @Override
    public List<MsgNotificationResponseVo> getMsgNotification(String category) throws ServiceException {
       throw new ServiceException();
    }
}
