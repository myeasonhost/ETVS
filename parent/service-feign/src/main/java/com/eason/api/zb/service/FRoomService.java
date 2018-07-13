package com.eason.api.zb.service;

import com.eason.api.base.vo.model.FileItemModel;
import com.eason.api.exception.ServiceException;
import com.eason.api.zb.IRoomService;
import com.eason.api.zb.service.impl.FRoomServiceImpl;
import com.eason.api.zb.vo.room.IsChargedResponseVo;
import com.eason.api.zb.vo.room.RoomInfoResponseVo;
import com.eason.api.zb.vo.room.RoomResponseVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-api-zb-impl", fallback = FRoomServiceImpl.class)
public interface FRoomService extends IRoomService {
    @RequestMapping(value = "/room/{roomId}/enterRoom", method = RequestMethod.GET)
    RoomResponseVo enterRoom(@RequestParam(value = "userId") Integer userId, @PathVariable(value = "roomId") Integer roomId) throws ServiceException;

    @RequestMapping(value = "/room/setRoomBackgroundImg", method = RequestMethod.POST)
    String setRoomBackgroundImg(@RequestParam(value = "userId") Integer userId,  @RequestBody FileItemModel fileImg) throws ServiceException;

    @RequestMapping(value = "/room/createRoom", method = RequestMethod.GET)
    RoomInfoResponseVo createRoom(@RequestParam(value = "userId")  Integer userId) throws ServiceException;
}
