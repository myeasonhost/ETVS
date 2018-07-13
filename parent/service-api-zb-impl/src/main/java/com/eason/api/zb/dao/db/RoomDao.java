package com.eason.api.zb.dao.db;

import com.eason.api.zb.dao.db.po.ZbTRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RoomDao extends JpaRepository<ZbTRoom, Integer> {

        @Modifying
        @Transactional
        @Query("UPDATE #{#entityName}  u SET u.roomStatus=?2 , u.roomTitle=?3 ,u.update_time=current_timestamp WHERE u.roomId=?1")
        void updateRoomStatusAndRoomTitle(Integer roomId, Integer status,String roomTitle);

        @Modifying
        @Transactional
        @Query("UPDATE #{#entityName}  u SET u.roomBgPic=?2 ,u.update_time=current_timestamp WHERE u.roomId=?1")
        void updateRoomBgPic(Integer roomId, String roomBgPic);

        ZbTRoom findByUserId(Integer userId);
}
