package com.eason.live.zb.service.impl;

import com.eason.api.zb.ILiveUrlService;
import com.eason.api.zb.exception.ServiceException;
import com.eason.api.zb.vo.live.ZbRoomLiveVo;
import com.eason.live.zb.config.TencentLiveConfig;
import com.eason.live.zb.dao.RoomLiveDao;
import com.eason.live.zb.po.ZbRoomLive;
import com.eason.live.zb.utils.AESOperator;
import com.eason.live.zb.utils.UrlUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tencent")
public class TencentLiveUrlServiceImpl implements ILiveUrlService {
    @Autowired
    private TencentLiveConfig liveConfig;
    @Autowired
    private RoomLiveDao roomLiveDao;

    @RequestMapping(value = "/getPushUrl",method = RequestMethod.GET)
    @Override
    public String getPushUrl(String roomId) throws ServiceException {
        if (StringUtils.isEmpty(roomId)){
            throw new ServiceException("roomId不能为空");
        }
        String pushUrl=liveConfig.getPushUrl().get(liveConfig.getLine());
        Date date= DateUtils.addDays(new Date(),1);
        Long txTime=date.getTime()/1000;
        String param= "bizid="+liveConfig.getBizid()+"&"+UrlUtils.getSafeUrl(liveConfig.getKey(),roomId,txTime);
        String zbCode=liveConfig.getBizid()+"_"+roomId+"_"+txTime;
        pushUrl=String.format(pushUrl,liveConfig.getBizid(),zbCode);

        //存入缓存
        ZbRoomLive zbRoomLive=roomLiveDao.findByRoomId(roomId);
        if (zbRoomLive!=null){
            roomLiveDao.delete(zbRoomLive);
        }
        zbRoomLive=new ZbRoomLive();
        zbRoomLive.setRoomId(roomId);
        zbRoomLive.setProducer(liveConfig.getProducer());
        zbRoomLive.setAppName(liveConfig.getAppName());
        zbRoomLive.setLine(liveConfig.getLine());
        zbRoomLive.setPushUrl(pushUrl+"?"+param);

        String playUrl=liveConfig.getPlayUrl().get(liveConfig.getLine());
        playUrl=String.format(playUrl,liveConfig.getBizid(),zbCode);
        zbRoomLive.setPlayUrl(playUrl);

        roomLiveDao.save(zbRoomLive);
        return AESOperator.encrypt(zbRoomLive.getPushUrl());
    }

    @RequestMapping(value = "/getPlayUrl",method = RequestMethod.GET)
    @Override
    public String getPlayUrl(String roomId) throws ServiceException{
        if (StringUtils.isEmpty(roomId)){
            throw new ServiceException("roomId不能为空");
        }
        ZbRoomLive zbRoomLive=roomLiveDao.findByRoomId(roomId);
        if (zbRoomLive==null){
            throw new ServiceException("该房间的流地址不存在");
        }
        return AESOperator.encrypt(zbRoomLive.getPlayUrl());
    }

    @Override
    public List<ZbRoomLiveVo> getPlayUrlList() throws ServiceException {
        return null;
    }

    @Override
    public String removeLiveInfo(String roomId) throws ServiceException {
        return null;
    }
}
