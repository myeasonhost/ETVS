package com.eason.api.zb;

import com.eason.api.exception.ServiceException;
import com.eason.api.zb.vo.user.UserResponseVo;
import com.eason.api.zb.vo.zhubo.StartPlayRequestVo;
import com.eason.api.zb.vo.zhubo.StartPlayResponseVo;
import com.eason.api.zb.vo.zhubo.ZhuboResponseVo;

import java.util.List;

/**
 * @apiDefine zhubo 主播API
 */
public interface IZhuboService {

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup zhubo
	 * @apiPermission Android/IOS
	 * @api {POST} /zhubo/startPlay?token=xxxxxx 1.开始直播
	 * @apiName startPlay
	 *
	 * @apiDescription
	 *	> 主播API - 开始直播</br>
	 * >  点击开始直播：</br>
	 *
	 *
	 * @apiParam (String) {String{0..10}} roomTitle 房间标题
	 * @apiParam (String)  roomBackgroundImg 	房间背景图片
	 *
	 * @apiSuccess {Integer} planId  场次Id
	 * @apiSuccess {Integer} roomId 房间Id
	 * @apiSuccess {String} result  开播提示信息
	 *
	 *
	 */
	public StartPlayResponseVo startPlay(Integer userId, StartPlayRequestVo startPlayRequestVo) throws ServiceException;


}
