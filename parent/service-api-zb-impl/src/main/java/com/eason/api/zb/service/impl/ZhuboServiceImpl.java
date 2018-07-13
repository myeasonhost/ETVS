package com.eason.api.zb.service.impl;

import com.eason.api.exception.ServiceException;
import com.eason.api.zb.IZhuboService;
import com.eason.api.zb.dao.db.RoomDao;
import com.eason.api.zb.dao.db.UserPoDao;
import com.eason.api.zb.dao.db.po.UserInfoPo;
import com.eason.api.zb.dao.db.po.ZbTRoom;
import com.eason.api.zb.dao.mongo.RoomPlanDao;
import com.eason.api.zb.dao.mongo.po.ZbTRoomPlan;
import com.eason.api.zb.vo.user.UserResponseVo;
import com.eason.api.zb.vo.zhubo.StartPlayRequestVo;
import com.eason.api.zb.vo.zhubo.StartPlayResponseVo;
import com.eason.api.zb.vo.zhubo.ZhuboResponseVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/zhubo")
public class ZhuboServiceImpl implements IZhuboService {
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private UserPoDao userPoDao;
    @Autowired
    private RoomPlanDao roomPlanDao;

    @RequestMapping(value = "/startPlay", method = RequestMethod.POST)
    @Override
    public StartPlayResponseVo startPlay(Integer userId, @RequestBody StartPlayRequestVo startPlayRequestVo) throws ServiceException {
        StartPlayResponseVo startPlayResponseVo=new StartPlayResponseVo();
        try {
            //1. 参数验证
            String title=startPlayRequestVo.getRoomTitle();
            if (StringUtils.isEmpty(title)){
                throw new ServiceException("房间标题不能为空");
            }
            if (title.length() > 10) {
                throw new ServiceException("房间标题最多10个汉字");
            }
            String roomBgPic=startPlayRequestVo.getRoomBackgroundImg();
            if (StringUtils.isEmpty(roomBgPic)){
                throw new ServiceException("房间封面不能为空");
            }
            ZbTRoom zbTRoom=this.roomDao.findByUserId(userId);
            if (zbTRoom ==null ){
                throw new ServiceException("当前用户的房间还未创建");
            }

            ZbTRoomPlan zbTRoomPlan=this.roomPlanDao.findByUserId(userId);
            //2.验证房间状态  0=未开播 1=已开播
            if (zbTRoomPlan!=null){
                this.roomDao.updateRoomStatusAndRoomTitle(zbTRoom.getRoomId(),1,title);

                zbTRoomPlan.setRoomTitle(title);
                this.roomPlanDao.save(zbTRoomPlan);
                startPlayResponseVo.setPlanId(zbTRoomPlan.getPlanId());
                startPlayResponseVo.setRoomId(zbTRoomPlan.getRoomId());
                startPlayResponseVo.setResult("正在直播中，重新进入");
                return startPlayResponseVo;
            }
            if (zbTRoomPlan == null){
                zbTRoomPlan =new ZbTRoomPlan();
                //更改房间直播状态，设置直播标题
               this.roomDao.updateRoomStatusAndRoomTitle(zbTRoom.getRoomId(),1,title);

                zbTRoomPlan.setRoomId(zbTRoom.getRoomId());
                zbTRoomPlan.setUserId(userId);
                zbTRoomPlan.setRoomTitle(title);
                zbTRoomPlan.setRoomBgPic(zbTRoom.getRoomBgPic());
                zbTRoomPlan.setRoomStatus(1);

                UserInfoPo userInfoPo=this.userPoDao.findOne(userId);
                zbTRoomPlan.setUsername(userInfoPo.getUsername());
                zbTRoomPlan.setNickname(userInfoPo.getNickname());
                zbTRoomPlan.setAvatar(userInfoPo.getAvatar());
                zbTRoomPlan.setSignature(userInfoPo.getSignature());
                zbTRoomPlan.setOpenTime(new Date());

                this.roomPlanDao.save(zbTRoomPlan);
                startPlayResponseVo.setResult("开播成功，开始直播");
            }
            startPlayResponseVo.setPlanId(zbTRoomPlan.getPlanId());
            startPlayResponseVo.setRoomId(zbTRoomPlan.getRoomId());
            return startPlayResponseVo;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
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
    public List<UserResponseVo> getGiftUserList(Integer zbId, String category) throws ServiceException {
        return null;
    }




    @Override
    public String overPlay(Integer userId, Integer planId) throws ServiceException {
        return null;
    }

}
