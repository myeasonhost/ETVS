package com.eason.live.zb.dao;

import com.eason.live.zb.po.ZbTQvodConfigs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QconfigDao extends JpaRepository<ZbTQvodConfigs, Integer> {
    @Query("select config FROM ZbTQvodConfigs config where config.aliasName=?1")
    ZbTQvodConfigs findByConfig(String aliasName);
}
