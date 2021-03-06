package com.eason.api.mapper;

import com.eason.api.po.user.UserDeviceInfoPo;
import org.apache.ibatis.annotations.Mapper;

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
