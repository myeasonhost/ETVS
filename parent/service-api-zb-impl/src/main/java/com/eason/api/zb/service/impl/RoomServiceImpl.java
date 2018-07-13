package com.eason.api.zb.service.impl;

import com.eason.api.base.vo.model.FileItemModel;
import com.eason.api.exception.ServiceException;
import com.eason.api.zb.IRoomService;
import com.eason.api.zb.dao.db.RoomDao;
import com.eason.api.zb.dao.db.po.ZbTRoom;
import com.eason.api.zb.vo.room.IsChargedResponseVo;
import com.eason.api.zb.vo.room.RoomInfoResponseVo;
import com.eason.api.zb.vo.room.RoomResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

@RestController
@RequestMapping("/room")
public class RoomServiceImpl implements IRoomService {
    private static Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Autowired
    private RoomDao roomDao;
    @Value("${zb.file.img.local}")
    private String fileImgLocal;
    @Value("${zb.file.img.remote}")
    private String fileImgRemote;

    @RequestMapping(value = "/{roomId}/enterRoom", method = RequestMethod.GET)
    @Override
    public RoomResponseVo enterRoom(Integer userId, Integer roomId) throws ServiceException {
        return null;
    }

    @RequestMapping(value = "/{roomId}/isCharged", method = RequestMethod.GET)
    @Override
    public IsChargedResponseVo isCharged(Integer userId, Integer roomId) throws ServiceException {
        return null;
    }

    @RequestMapping(value = "/setRoomBackgroundImg", method = RequestMethod.POST)
    @Override
    public String setRoomBackgroundImg(Integer userId,  @RequestBody FileItemModel fileImg) throws ServiceException {
        try {
            ZbTRoom zbTRoom=roomDao.findByUserId(userId);
            if (zbTRoom == null){
                throw  new ServiceException("当前用户还没有开直播间");
            }
            FileCopyUtils.copy(fileImg.getContent(), new File(fileImgLocal + fileImg.getFileName()));
            String pic = fileImgRemote + fileImg.getFileName();
            this.roomDao.updateRoomBgPic(zbTRoom.getRoomId(), pic);
            return fileImgRemote + fileImg.getFileName();
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @RequestMapping(value = "/createRoom", method = RequestMethod.GET)
    @Override
    public RoomInfoResponseVo createRoom(Integer userId) throws ServiceException {
        RoomInfoResponseVo roomInfoResponseVo=new RoomInfoResponseVo();
        try {
            ZbTRoom zbTRoom=roomDao.findByUserId(userId);
            if (zbTRoom == null){
               zbTRoom=new ZbTRoom();
               zbTRoom.setUserId(userId);
                zbTRoom.setRoomStatus(0);   //0为开播
               zbTRoom.setCreate_time(new Timestamp(System.currentTimeMillis()));
               zbTRoom.setUpdate_time(new Timestamp(System.currentTimeMillis()));
               zbTRoom.setIsVideo(0);   //0=不启用，1=启用
                zbTRoom= this.roomDao.save(zbTRoom);
                roomInfoResponseVo.setRoomId(zbTRoom.getRoomId());
                roomInfoResponseVo.setRoomStatus(0);
                roomInfoResponseVo.setResult("直播间初次创建成功");
               return roomInfoResponseVo;
            }else{
                roomInfoResponseVo.setRoomId(zbTRoom.getRoomId());
                roomInfoResponseVo.setRoomTitle(zbTRoom.getRoomTitle());
                roomInfoResponseVo.setRoomBgPic(zbTRoom.getRoomBgPic());
                roomInfoResponseVo.setRoomStatus(zbTRoom.getRoomStatus());
                if (zbTRoom.getRoomStatus()==1){
                    roomInfoResponseVo.setResult("直播间正在直播");
                }else {
                    roomInfoResponseVo.setResult("直播间未开播");
                }
                return roomInfoResponseVo;
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
