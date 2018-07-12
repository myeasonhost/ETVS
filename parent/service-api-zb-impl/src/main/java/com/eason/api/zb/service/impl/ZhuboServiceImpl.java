package com.eason.api.zb.service.impl;

import com.eason.api.exception.ServiceException;
import com.eason.api.zb.IIndexService;
import com.eason.api.zb.IZhuboService;
import com.eason.api.zb.cache.ZbTRoomPlan;
import com.eason.api.zb.dao.*;
import com.eason.api.zb.model.PageModel;
import com.eason.api.zb.model.ZbConstant;
import com.eason.api.zb.po.*;
import com.eason.api.zb.vo.index.IndexResponseVo;
import com.eason.api.zb.vo.room.RoomStatResponseVo;
import com.eason.api.zb.vo.user.UserLevelRankResponseVo;
import com.eason.api.zb.vo.user.UserResponseVo;
import com.eason.api.zb.vo.zhubo.ReadyPlayResponseVo;
import com.eason.api.zb.vo.zhubo.StartPlayRequestVo;
import com.eason.api.zb.vo.zhubo.StartPlayResponseVo;
import com.eason.api.zb.vo.zhubo.ZhuboResponseVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/zhubo")
public class ZhuboServiceImpl implements IZhuboService {
    @Override
    public String apply(Integer userId) throws ServiceException {
        return null;
    }

    @Override
    public List<ZhuboResponseVo> getZhuboList(Integer userId, Integer num) throws ServiceException {
        return null;
    }

    @Override
    public ZhuboResponseVo getZbDetail(Integer userId, Integer zbId) throws ServiceException {
        return null;
    }

    @Override
    public List<UserLevelRankResponseVo> getAttentionUserList(Integer zbId) throws ServiceException {
        return null;
    }

    @Override
    public List<UserResponseVo> getGiftUserList(Integer zbId, String category) throws ServiceException {
        return null;
    }

    @Override
    public ReadyPlayResponseVo getReadyPlayInfo(Integer userId, String token) throws ServiceException {
        return null;
    }

    @Override
    public StartPlayResponseVo startPlay(Integer userId, StartPlayRequestVo startPlayRequestVo) throws ServiceException {
        return null;
    }

    @Override
    public String overPlay(Integer userId, Integer planId) throws ServiceException {
        return null;
    }

    @Override
    public RoomStatResponseVo getStat(Integer userId, Integer planId) throws ServiceException {
        return null;
    }
}
