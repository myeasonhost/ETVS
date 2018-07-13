package com.eason.api.zb.service;

import com.eason.api.zb.IZhuboService;
import com.eason.api.exception.ServiceException;
import com.eason.api.zb.service.impl.FZhuboServiceImpl;
import com.eason.api.zb.vo.user.UserLevelRankResponseVo;
import com.eason.api.zb.vo.user.UserResponseVo;
import com.eason.api.zb.vo.zhubo.ReadyPlayResponseVo;
import com.eason.api.zb.vo.zhubo.StartPlayRequestVo;
import com.eason.api.zb.vo.zhubo.StartPlayResponseVo;
import com.eason.api.zb.vo.zhubo.ZhuboResponseVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-api-zb-impl", fallback = FZhuboServiceImpl.class)
public interface FZhuboService extends IZhuboService {
    @RequestMapping(value = "/zhubo/startPlay", method = RequestMethod.POST)
    StartPlayResponseVo startPlay(@RequestParam(value = "userId") Integer userId, @RequestBody StartPlayRequestVo startPlayRequestVo) throws ServiceException;
}
