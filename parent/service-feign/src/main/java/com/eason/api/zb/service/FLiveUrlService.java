package com.eason.api.zb.service;

import com.eason.api.zb.ILiveUrlService;
import com.eason.api.zb.exception.ServiceException;
import com.eason.api.zb.service.impl.FLiveUrlServiceImpl;
import com.eason.api.zb.vo.live.ZbRoomLiveVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "zb-live-media", fallback = FLiveUrlServiceImpl.class)
public interface FLiveUrlService extends ILiveUrlService {
    @RequestMapping(value = "/live/getPushUrl", method = RequestMethod.GET)
    String getPushUrl(@RequestParam(value = "roomId") String roomId) throws ServiceException;

    @RequestMapping(value = "/live/getPlayUrl", method = RequestMethod.GET)
    String getPlayUrl(@RequestParam(value = "roomId") String roomId) throws ServiceException;

    @RequestMapping(value = "/live/getPlayUrlList",method = RequestMethod.GET)
    List<ZbRoomLiveVo> getPlayUrlList() throws ServiceException;

    @RequestMapping(value = "/live/removeLiveInfo",method = RequestMethod.GET)
    String removeLiveInfo(@RequestParam(value = "roomId") String roomId) throws ServiceException;
}
