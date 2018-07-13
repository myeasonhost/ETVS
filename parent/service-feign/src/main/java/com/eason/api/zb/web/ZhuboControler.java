package com.eason.api.zb.web;

import com.eason.api.base.vo.response.ResponseVo;
import com.eason.api.exception.ServiceException;
import com.eason.api.zb.service.FLiveUrlService;
import com.eason.api.zb.service.FZhuboService;
import com.eason.api.zb.vo.zhubo.ReadyPlayResponseVo;
import com.eason.api.zb.vo.zhubo.StartPlayRequestVo;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/zhubo")
public class ZhuboControler {
    private static Logger log = LoggerFactory.getLogger(ZhuboControler.class);

    @Autowired
    private FZhuboService zhuboServiceImpl;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private FLiveUrlService liveUrlServiceImpl;

    @RequestMapping(value = "/startPlay", method = RequestMethod.POST)
    public ResponseVo startPlay(HttpServletRequest request,  @RequestBody  StartPlayRequestVo startPlayRequestVo) {
        try {
            Integer userId=null;
            String api_token=request.getHeader("token");
            if (StringUtils.isEmpty(api_token)){
                api_token = request.getParameter("token");
            }
            String id = stringRedisTemplate.opsForValue().get("token:"+api_token);
            if (id == null) {
                throw new ServiceException("您的账号已在异地登陆，请您重新登陆");
            }else{
                userId=Integer.parseInt(id);
            }

            ResponseVo responseVo = new ResponseVo(0, "操作成功");
            responseVo.setData(zhuboServiceImpl.startPlay(userId, startPlayRequestVo));
            return responseVo;
        } catch (ServiceException e) {
            ResponseVo responseVo = new ResponseVo(401, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (HystrixRuntimeException e) {
            ResponseVo responseVo = new ResponseVo(500, "服务器忙，请重试！");
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (Exception e) {
            ResponseVo responseVo = new ResponseVo(500, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        }
    }
}
