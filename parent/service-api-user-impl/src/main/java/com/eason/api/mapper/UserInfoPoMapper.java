package com.eason.api.mapper;

import com.eason.api.po.user.UserInfoPo;

public interface UserInfoPoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoPo record);

    int insertSelective(UserInfoPo record);

    UserInfoPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfoPo record);

    int updateByPrimaryKey(UserInfoPo record);
}