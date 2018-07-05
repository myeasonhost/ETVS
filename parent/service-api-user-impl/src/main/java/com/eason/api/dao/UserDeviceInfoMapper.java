package com.eason.api.dao;

import com.eason.api.po.user.UserDeviceInfoPo;

/**
 * UserDeviceInfoDAO接口
 *
 * @author eason
 */
public interface UserDeviceInfoMapper{
	public int insertUserDeviceInfo(UserDeviceInfoPo po);
	public UserDeviceInfoPo getUserDeviceByPo(UserDeviceInfoPo po);
	public int updateUserDeviceByClear(UserDeviceInfoPo po);
	public int updateUserDeviceByPo(UserDeviceInfoPo po);
}
