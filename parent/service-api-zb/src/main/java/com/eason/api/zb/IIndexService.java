package com.eason.api.zb;

import com.eason.api.exception.ServiceException;
import com.eason.api.zb.model.PageModel;
import com.eason.api.zb.vo.index.BannerResponseVo;
import com.eason.api.zb.vo.index.IndexResponseVo;
import com.eason.api.zb.vo.index.MsgNotificationResponseVo;

import java.util.List;

/**
 * @apiDefine index 直播首页API
 */
public interface IIndexService {

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup index
	 * @api {GET} /index/{category}/getIndexList/{position}/{pageSize} 1.房间列表
	 * @apiName getIndexList
	 *
	 *
	 * @apiDescription
     * >  房间分类：1=最热（默认）、2=最新 </br>
	 * > 没有这么多需求，默认就传category=1
	 * @apiSuccess {Integer} total 		总数
	 * @apiSuccess {Integer} rows 		rows->row
	 * @apiSuccess {Integer} row.roomId 		房间ID
	 * @apiSuccess {Integer} row.userId 		主播ID（用户ID）
	 * @apiSuccess {Integer} row.roomPlanId 		房间场次ID
	 * @apiSuccess {String} row.username 	主播账号（用户名）
	 * @apiSuccess {String} row.nickname 	主播昵称（用户昵称）
	 * @apiSuccess {String} row.avatar 	主播头像
	 * @apiSuccess {String} row.roomTitle  	房间标题
	 * @apiSuccess {Integer} row.onlineUser  真实在线用户
	 * @apiSuccess {Integer} row.machineUser 机器用户
	 * @apiSuccess {Integer} row.viewCount 	总浏览次数
	 * @apiSuccess {Integer} row.watchCount 	总观看次数
	 * @apiSuccess {String} row.roomBackgroundImg  房间背景图片
	 * @apiSuccess {Integer} row.roomStatus  	直播状态： 1=直播中（2=回放中）
	 * @apiSuccess {Timestamp} row.startTime  	房间开播时间
	 *
	 */
	public PageModel<IndexResponseVo> getIndexList(String category, Integer position, Integer pageSize) throws ServiceException;

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup index
	 * @api {GET} /index/{category}/getBannerList 2.直播首页Banner列表
	 * @apiName getBannerList
	 *
	 *
	 * @apiDescription
	 * > 需要显示Banner的模块：最新、最热</br>
     * >category 房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏</br>
	 * > 没有这么多需求，默认就传category=1
	 *
	 * @apiSuccess {Integer} id 	BannerID
	 * @apiSuccess {String} title 	Banner标题
	 * @apiSuccess {String} thumb_img_url 	Banner图片地址
	 * @apiSuccess {String} url 	跳转地址
	 * @apiSuccess {String} url_type  Banner跳转类型（1:链接 2:应用内界面 3:指定视频）
	 * @apiSuccess {String} status  状态（1=正常、0=删除）
	 *
	 */
	public List<BannerResponseVo> getBannerList(String category) throws ServiceException;

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup index
	 * @api {GET} /index/{category}/getMsgNotificationList 3.公告消息列表
	 * @apiName getMsgNotificationList
	 *
	 *
	 * @apiDescription
	 * > 需要显示MsgNotification的模块：最新、最热</br>
     * >category 房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏</br>
	 *> 没有这么多需求，默认就传category=1
	 *
	 * @apiSuccess {Integer} id 		公告消息ID
	 * @apiSuccess {String} title 	公告消息内容
	 * @apiSuccess {String} url 	点击事件
	 *
	 */
	public List<MsgNotificationResponseVo> getMsgNotification(String category) throws ServiceException;
}
