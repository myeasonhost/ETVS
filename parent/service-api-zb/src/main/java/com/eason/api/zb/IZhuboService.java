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
	 * @api {GET} /zhubo/getZhuboList/{num}  主播推荐列表
	 * @apiName getZhuboList
	 *
	 * @apiDescription
	 * > 点击收藏，没有关注列表，显示热门推荐主播</br>
	 * > 如果没有登陆，显示热门推荐主播</br>
	 *> 如果有登陆，显示已关注的主播</br>
	 * @apiSuccess {Integer} zbId	主播ID
	 * @apiSuccess {String} zbNickname	主播昵称
	 * @apiSuccess {String} zbLevel		主播等级
	 * @apiSuccess {String} zbHeadImg 	主播头像
	 * @apiSuccess {Integer} isAttention 用户是否关注 0 =未关注，1=已关注
	 *
	 */
	public List<ZhuboResponseVo> getZhuboList(Integer userId,Integer num) throws ServiceException;


	/**
	 * @apiVersion 1.0.0
	 * @apiGroup zhubo
	 * @apiPermission Android/IOS
	 * @api {GET} /zhubo/getZbDetail/{zbId} 主播详情
	 * @apiName getZbDetail
	 *
	 * @apiDescription
	 * > 点击主播头像，进入主播迷你卡，获取主播详情</br>
	 *
	 * @apiSuccess {Integer} userId	用户ID
	 * @apiSuccess {String} zbNickname		主播昵称
	 * @apiSuccess {String} signature		用户签名
	 * @apiSuccess {Integer} sex		用户性别
	 * @apiSuccess {String} userHeadImg 	主播头像
	 * @apiSuccess {Integer} zbLevel		主播等级
	 * @apiSuccess {Integer} userLevel		用户等级
	 * @apiSuccess {Integer} vipLevel		用户VIP等级
	 *
	 * @apiSuccess {Integer} isAttention 用户是否关注（0=未关注，1=已关注）
	 * @apiSuccess {Integer} attentionUserTotal 		粉丝
	 * @apiSuccess {Integer} diamondGiftZBTotal   收礼
	 *
	 */
	public ZhuboResponseVo getZbDetail(Integer userId,Integer zbId) throws ServiceException;


	/**
	 * @apiVersion 1.0.0
	 * @apiGroup zhubo
	 * @apiPermission Android/IOS
	 * @api {GET} /zhubo/{zbId}/getGiftUserList/{category} 主播收礼排行用户列表
	 * @apiName getGiftUserList
	 *
	 * @apiDescription
	 * > 点击钻石礼物，弹出礼物排行对话框</br>
	 * > 	category =today，history</br>
	 * > （1）点击当日，获取当日送礼排行</br>
	 * > （2）点击全部，获取历史送礼排行</br>
	 *
	 *
	 * @apiSuccess {Object[]} userList		userList->user
	 * @apiSuccess {Integer} userList.user.userId		用户ID
	 * @apiSuccess {String} userList.user.nickName	用户昵称
	 * @apiSuccess {Integer} userList.user.sex	用户性别
	 * @apiSuccess {String} userList.user.userHeadImg 	用户头像
	 * @apiSuccess {Integer} userList.user.userLevel 	用户等级
	 * @apiSuccess {Integer} userList.user.giftRankNo1	 当前送礼排行
	 * @apiSuccess {String} userList.user.diamondGiftUserTotal 当前用户在当前房间累计送礼总数
	 *
	 */
	public List<UserResponseVo> getGiftUserList(Integer zbId, String category) throws ServiceException;




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


	/**
	 * @apiVersion 1.0.0
	 * @apiGroup zhubo
	 * @apiPermission Android/IOS
	 * @api {GET} /zhubo/overPlay/{planId} 结束直播
	 * @apiName overPlay
	 *
	 * @apiDescription
	 * > 点击确认退出，结束直播</br>
	 * > （1）记录直播视频状态is_video=0</br>
	 *> （2）调Medie接口删除视频</br>
	 * @apiSuccess {String} result  退出成功或者失败
	 *
	 */
	public String overPlay(Integer userId,Integer planId) throws ServiceException;


}
