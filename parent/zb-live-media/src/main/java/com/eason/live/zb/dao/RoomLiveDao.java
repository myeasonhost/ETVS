package com.eason.live.zb.dao;

import com.eason.live.zb.po.ZbRoomLive;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface RoomLiveDao extends MongoRepository<ZbRoomLive, BigInteger>{
    ZbRoomLive findByRoomId(String roomId);
}
