package com.eason.api.zb.dao;

import com.eason.api.zb.po.ZbTQvodZbTRoomTypePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomTypePriceDao extends JpaRepository<ZbTQvodZbTRoomTypePrice, Integer> {

    List<ZbTQvodZbTRoomTypePrice> findByType(String type);

    ZbTQvodZbTRoomTypePrice findByTypeAndAndTime(String type,Integer time);

    @Query(value = "SELECT t.type,GROUP_CONCAT(t.time SEPARATOR '|'), GROUP_CONCAT(t.price SEPARATOR '|') , GROUP_CONCAT(t.time_interval SEPARATOR '|') " +
            "FROM qvod_zb_t_room_type_price t  WHERE t.`type`=?1 GROUP BY t.type ",nativeQuery = true)
    List<Object[]> listByRoomPriceList(String type);
}
