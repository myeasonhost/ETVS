package com.eason.api.zb.service.impl;

import com.eason.api.base.vo.model.FileItemModel;
import com.eason.api.exception.ServiceException;
import com.eason.api.zb.service.FRoomService;
import com.eason.api.zb.vo.room.IsChargedResponseVo;
import com.eason.api.zb.vo.room.RoomInfoResponseVo;
import com.eason.api.zb.vo.room.RoomResponseVo;
import org.springframework.stereotype.Component;

@Component("roomServiceImpl")
public class FRoomServiceImpl implements FRoomService {
    @Override
    public RoomResponseVo enterRoom(Integer userId, Integer roomId) throws ServiceException {
       throw new ServiceException();
    }

    @Override
    public String setRoomBackgroundImg(Integer userId, FileItemModel fileImg) throws ServiceException {
       throw new ServiceException();
    }

    @Override
    public RoomInfoResponseVo createRoom(Integer userId) throws ServiceException {
        return null;
    }
}
