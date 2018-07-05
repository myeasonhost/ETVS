//package com.eason.live.zb.service.impl;
//
//import com.eason.api.zb.ILiveUrlService;
//import com.eason.api.zb.exception.ServiceException;
//import com.eason.live.zb.config.JsyLiveConfig;
//import org.apache.commons.lang.time.DateUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//@RestController
//@RequestMapping("/jsy")
//public class JSYLiveUrlServiceImpl implements ILiveUrlService {
//    @Autowired
//    private JsyLiveConfig liveConfig;
//
//    @RequestMapping(value = "/getPushUrl",method = RequestMethod.GET)
//    @Override
//    public String getPushUrl(String roomId)  throws ServiceException {
//        String targetUrl=liveConfig.getPushUrl().get(liveConfig.getLine());
//        Date date= DateUtils.addDays(new Date(),1);
//        Long txTime=date.getTime()/1000;
//        String zbCode=roomId+"_"+txTime;
//        targetUrl=String.format(targetUrl,zbCode);
//        return targetUrl;
//    }
//
//    @RequestMapping(value = "/getPlayUrl",method = RequestMethod.GET)
//    @Override
//    public String getPlayUrl(String roomId)  throws ServiceException {
//        String targetUrl=liveConfig.getPlayUrl().get(liveConfig.getLine());
//        Date date= DateUtils.addDays(new Date(),1);
//        Long txTime=date.getTime()/1000;
//        String zbCode=roomId+"_"+txTime;
//        targetUrl=String.format(targetUrl,zbCode);
//        return targetUrl;
//    }
//}
