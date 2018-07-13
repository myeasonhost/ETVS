package com.eason.api.zb.web;

import com.eason.api.base.vo.response.ResponseVo;
import com.eason.api.exception.ServiceException;
import com.eason.api.zb.service.FIndexService;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/index")
public class IndexControler  {
    @Autowired
    private FIndexService indexServiceImpl;

    @RequestMapping(value = "/{category}/getIndexList/{position}/{pageSize}",method = RequestMethod.GET)
    public ResponseVo getIndexList(@PathVariable String category, @PathVariable Integer position, @PathVariable Integer pageSize) {
        try {
            ResponseVo responseVo=new ResponseVo(0,"操作成功");
            responseVo.setData(indexServiceImpl.getIndexList(category,position,pageSize));
            return responseVo;
        } catch (HystrixRuntimeException e) {
            ResponseVo responseVo = new ResponseVo(500, "服务器忙，请重试！");
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (Exception e) {
            ResponseVo responseVo=new ResponseVo(500,e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        }
    }

    @RequestMapping(value = "/{category}/getMsgNotificationList",method = RequestMethod.GET)
    public ResponseVo getMsgNotificationList(@PathVariable String category) {
        try {
            ResponseVo responseVo=new ResponseVo(0,"操作成功");
            responseVo.setData(indexServiceImpl.getMsgNotification(category));
            return responseVo;
        } catch (HystrixRuntimeException e) {
            ResponseVo responseVo = new ResponseVo(500, "服务器忙，请重试！");
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (Exception e) {
            ResponseVo responseVo=new ResponseVo(500,e.getMessage());
            responseVo.setData(new ArrayList<>());
            return responseVo;
        }
    }

    @RequestMapping(value = "/{category}/getBannerList",method = RequestMethod.GET)
    public ResponseVo getBannerList(@PathVariable String category) {
        try {
            ResponseVo responseVo=new ResponseVo(0,"操作成功");
            responseVo.setData(indexServiceImpl.getBannerList(category));
            return responseVo;
        } catch (HystrixRuntimeException e) {
            ResponseVo responseVo = new ResponseVo(500, "服务器忙，请重试！");
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (Exception e) {
            ResponseVo responseVo=new ResponseVo(500,e.getMessage());
            responseVo.setData(new ArrayList<>());
            return responseVo;
        }
    }
}
