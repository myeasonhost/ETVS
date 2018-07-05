package com.eason.live.zb.service.impl;

import com.eason.api.zb.ILiveUrlService;
import com.eason.api.zb.exception.ServiceException;
import com.eason.api.zb.vo.live.ZbRoomLiveVo;
import com.eason.live.zb.config.JsyLiveConfig;
import com.eason.live.zb.dao.QconfigDao;
import com.eason.live.zb.dao.RoomLiveDao;
import com.eason.live.zb.dao.RoomPlanDao;
import com.eason.live.zb.po.ZbRoomLive;
import com.eason.live.zb.po.ZbTQvodConfigs;
import com.eason.live.zb.po.ZbTRoomPlan;
import com.eason.live.zb.utils.AESOperator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/live")
public class LiveUrlServiceImpl implements ILiveUrlService {
    @Autowired
    private DnionUrlServiceImpl dnionUrlServiceImpl;
    @Autowired
    private TencentLiveUrlServiceImpl tencentLiveUrlServiceImpl;
    @Autowired
    private QconfigDao qconfigDao;
    @Autowired
    private RoomLiveDao roomLiveDao;
    @Autowired
    private RoomPlanDao roomPlanDao;

    @RequestMapping(value = "/getPushUrl",method = RequestMethod.GET)
    @Override
    public String getPushUrl(String roomId)  throws ServiceException {
        if (StringUtils.isEmpty(roomId)){
            throw new ServiceException("roomId不能为空");
        }
        ZbTQvodConfigs config=this.qconfigDao.findByConfig("zb_live");
        if (config==null){
            return dnionUrlServiceImpl.getPushUrl(roomId);
        }
        if ("d".equalsIgnoreCase(config.getDescription())){
            return dnionUrlServiceImpl.getPushUrl(roomId);
        }
        if ("t".equalsIgnoreCase(config.getDescription())){
            return tencentLiveUrlServiceImpl.getPushUrl(roomId);
        }
        return dnionUrlServiceImpl.getPushUrl(roomId);
    }

    @RequestMapping(value = "/getPlayUrl",method = RequestMethod.GET)
    @Override
    public String getPlayUrl(String roomId)  throws ServiceException {
        ZbTQvodConfigs config=this.qconfigDao.findByConfig("zb_live");
        if (config==null){
            return dnionUrlServiceImpl.getPlayUrl(roomId);
        }
        if ("d".equalsIgnoreCase(config.getDescription())){
            return dnionUrlServiceImpl.getPlayUrl(roomId);
        }
        if ("t".equalsIgnoreCase(config.getDescription())){
            return tencentLiveUrlServiceImpl.getPlayUrl(roomId);
        }
        return dnionUrlServiceImpl.getPlayUrl(roomId);
    }

    @RequestMapping(value = "/getPlayUrlList",method = RequestMethod.GET)
    @Override
    public List<ZbRoomLiveVo> getPlayUrlList() throws ServiceException {
        List<ZbRoomLive> list=this.roomLiveDao.findAll();
        List<ZbTRoomPlan> planList=this.roomPlanDao.findAll();
        List<Integer> roomIds=new ArrayList<>();
        planList.forEach(zbTRoomPlan -> {
            roomIds.add(zbTRoomPlan.getRoomId());
        });
        List<ZbRoomLiveVo> listVo=new ArrayList<>(list.size());
        list.forEach(zbRoomLive -> {
            if (roomIds.contains(Integer.parseInt(zbRoomLive.getRoomId()))){
                listVo.add(new ZbRoomLiveVo(zbRoomLive.getId(),zbRoomLive.getRoomId(),zbRoomLive.getPlayUrl()));
            }else{
                this.roomLiveDao.delete(zbRoomLive);
            }
        });
        return listVo;
    }

    @RequestMapping(value = "/removeLiveInfo",method = RequestMethod.GET)
    @Override
    public String removeLiveInfo(String roomId) throws ServiceException {
        ZbRoomLive zbRoomLive=this.roomLiveDao.findByRoomId(roomId);
        if (zbRoomLive!=null){
            this.roomLiveDao.delete(zbRoomLive);
        }
        return "删除成功";
    }
}
