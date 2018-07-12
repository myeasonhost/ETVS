package com.eason.api.zb;

import com.eason.api.zb.dao.*;
import com.eason.api.zb.model.ZbConstant;
import com.eason.api.zb.po.ZbTRecrecordsLog;
import com.eason.api.zb.po.ZbTRoom;
import com.eason.api.zb.service.impl.ZhuboServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomTests {
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private ZhuboServiceImpl zhuboServiceImpl;
	@Resource
	private MongoTemplate mongoTemplate;
	@Test
	public void addRoom() {
		ZbTRoom room=new ZbTRoom();
		room.setZbId(1);
		room.setRoomTitle("提莫的直播间");
		room.setRoomInfo("可爱的美女的直播间····");
		room.setRoomBgPic("http://ZbTRoom.png");
		room.setRoomStatus(ZbConstant.Room.status.room_ing);
		room.setIsVideo(ZbConstant.Room.video.enable);
		room.setOrderField(1);
		room.setCreate_Time(new Timestamp(System.currentTimeMillis()));
//		roomDao.save(room);
	}

}
