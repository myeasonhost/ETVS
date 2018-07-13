package com.eason.api.zb.dao.db;

import com.eason.api.zb.dao.db.po.UserInfoPo;
import com.eason.api.zb.dao.db.po.ZbTRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserPoDao extends JpaRepository<UserInfoPo, Integer> {

}
