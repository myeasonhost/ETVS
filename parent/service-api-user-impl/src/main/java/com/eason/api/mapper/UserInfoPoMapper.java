package com.eason.api.mapper;

import com.eason.api.po.user.UserInfoPo;

public interface UserInfoPoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfoPo record);

    int insertSelective(UserInfoPo record);

    UserInfoPo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfoPo record);

    int updateByPrimaryKey(UserInfoPo record);

	UserInfoPo getUserByAccount(UserInfoPo userPo);
	
//	UserDetailVo getUserDetail(UserInfoPo userPo);
}