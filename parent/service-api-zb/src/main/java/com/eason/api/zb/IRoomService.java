package com.eason.api.zb;

import com.eason.api.base.vo.model.FileItemModel;
import com.eason.api.exception.ServiceException;
import com.eason.api.zb.vo.room.IsChargedResponseVo;
import com.eason.api.zb.vo.room.RoomInfoResponseVo;
import com.eason.api.zb.vo.room.RoomResponseVo;

/**
 * @apiDefine room 房间API
 */
public interface IRoomService {

    /**
     * @apiVersion 1.0.0
     * @apiGroup room
     * @apiPermission Android/IOS
     * @api {GET} /room/{roomId}/enterRoom 进入房间
     * @apiName enterRoom
     * @apiDescription > 用户与主播，进入房间，获取房间详情明细，包含如下几个动作：</br>
     * > （1）获取房间详情</br>
     * > （2）获取主播详情</br>
     * > （3）获取用户详情</br>
     * > （4）获取粉丝用户列表</br>
     * > （5）获取IM/Video服务器地址</br>
     * @apiSuccess {Integer} planId 		房间场次ID
     * @apiSuccess {Integer} roomId 		房间ID
     * @apiSuccess {String} roomTitle  	房间标题
     * @apiSuccess {String} roomType   	房间类型：'normal=普通房间','ticket=门票房间','time=时常房间','personal=私密房间','game=游戏房间'
     * @apiSuccess {Integer} onlineUser  房间当前在线用户
     * @apiSuccess {Integer} machineUser 房间机器人用户
     * @apiSuccess {Integer} diamondGiftNum	 房间钻石礼物总数
     * @apiSuccess {Integer} roomNo1   	房间排名
     * @apiSuccess {String} roomBackgroundImg  房间背景图片
     * @apiSuccess {Integer} zbId 		主播ID
     * @apiSuccess {Integer} userId 		主播的用户ID
     * @apiSuccess {String} zbNickname	主播昵称
     * @apiSuccess {Integer} zbLevel		主播等级
     * @apiSuccess {String} zbHeadImg 	主播头像
     * @apiSuccess {String} zbSignature 主播个性签名
     * @apiSuccess {Integer} isAttention 用户是否关注
     * @apiSuccess {String}  userLevel 用户等级息
     * @apiSuccess {String} download_url  下载地址
     * @apiSuccess {Double} diamondBalance  用户钻石余额（星钻）
     * @apiSuccess {Object[]} diamondRankList		diamondRankList->user（送礼钻石排行）
     * @apiSuccess {Integer} diamondRankList.user.userId		用户ID
     * @apiSuccess {String} diamondRankList.user.nickName	用户昵称
     * @apiSuccess {String} diamondRankList.user.sex	用户性别
     * @apiSuccess {String} diamondRankList.user.userHeadImg 用户头像
     * @apiSuccess {Integer} diamondRankList.user.userLevel 用户等级
     * @apiSuccess {Integer} diamondRankList.user.giftRankNo1	 当前送礼排行
     * @apiSuccess {Integer} diamondRankList.user.diamondGiftUserTotal 当前用户在当前房间累计送礼总数
     * @apiSuccess {Object[]} userLevelRankList		userLevelRankList->user（用户等级排行）
     * @apiSuccess {Integer} userLevelRankList.user.userId		用户ID
     * @apiSuccess {String} userLevelRankList.user.nickName	用户昵称
     * @apiSuccess {String} userLevelRankList.user.sex	用户性别
     * @apiSuccess {String} userLevelRankList.user.userHeadImg 用户头像
     * @apiSuccess {Integer} userLevelRankList.user.userLevel 用户等级
     * @apiSuccess {Object} media
     * @apiSuccess {Integer} media.type 视频流类型
     * @apiSuccess {String} media.url   视频流地址
     * @apiSuccess {Object} im
     * @apiSuccess {Integer} im.type 即时通讯类型
     * @apiSuccess {String} im.url   即时通讯地址
     * @apiSuccess {String} im.port  即时通讯端口
     */
    public RoomResponseVo enterRoom(Integer userId, Integer roomId) throws ServiceException;


    /**
     * @apiVersion 1.0.0
     * @apiGroup room
     * @apiPermission Android/IOS
     * @api {GET} /room/{roomId}/isCharged 房间是否收费
     * @apiName isCharged
     * @apiDescription > 判断房间是否收费，获取收费条件等信息</br>
     *> （1）房间收费类型roomType=ticket，time，personal</br>
     *> （2）2018-5-15日，新需求接口升级：房间收费接口—>改名为点击房间权限判断接口（权限优先级为：是否禁止入内、是否收费）</br>
     *> （A）流程上发生变化：先判断是否禁止入内（新需求），在判断是否收费（原有逻辑不变）</br>
     * > （B）在原结构上，多加了一个bannedInfo字段，如果bannedInfo不为空，表示禁止入内，禁止的原因在bannedInfo结构体里面，bannedInfo==null，表示允许入内，在判断是否收费</br>
     * @apiSuccess (ticket Success 200) {Integer} roomId 		房间ID
     * @apiSuccess (ticket Success 200) {Integer} zbId 		主播ID
     * @apiSuccess (ticket Success 200) {String="normal","ticket","time","personal","game"} roomType 	房间类型
     * @apiSuccess (ticket Success 200) {Integer} ticketStatus 0=未购买，1=购买
     * @apiSuccess (ticket Success 200) {Integer} selectPrice   门票单价
     * @apiSuccess (ticket Success 200)  {Integer} userId	试看用户ID
     * @apiSuccess (ticket Success 200) {Integer} isTrySee 0=未试看，1=已试看
     * @apiSuccess (ticket Success 200) {String} allowTime  允许试看时间
     * @apiSuccess (ticket Success 200) {Long} usedTime  已播时长
     * @apiSuccess (ticket Success 200) {Long} remainTime  剩余时长
     *
     * @apiSuccess (time Success 200) {Integer} roomId 		房间ID
     * @apiSuccess (time Success 200) {Integer} zbId 		主播ID
     * @apiSuccess (time Success 200) {String="normal","ticket","time","personal","game"} roomType 		房间类型
     * @apiSuccess (time Success 200) {Integer} selectPrice 时长单价
     * @apiSuccess (time Success 200) {Integer} timeInterval  时常间隔（收费间隔）
     * @apiSuccess (time Success 200)  {Integer} userId	试看用户ID
     * @apiSuccess (time Success 200) {Integer} isTrySee 0=未试看，1=已试看
     * @apiSuccess (time Success 200) {String} allowTime  允许试看时间
     * @apiSuccess (time Success 200) {Long} usedTime  已播时长
     * @apiSuccess (time Success 200) {Long} remainTime  剩余时长
     *
     * @apiSuccess (personal Success 200) {Integer} roomId 		房间ID
     * @apiSuccess (personal Success 200) {Integer} zbId 		主播ID
     * @apiSuccess (personal Success 200) {String="normal","ticket","time","personal","game"} roomType 		房间类型
     * @apiSuccess (personal Success 200)  {Integer} personalStatus 0=未预约，1=已预约
     * @apiSuccess (personal Success 200)  {Integer} userId	试看用户ID
     *
     * @apiSuccess (bannedInfo Success 200)  {Integer} setSeconds 设置的有效时间 ；-1永久被管理员禁言 其他是固定时间，0 未被禁足 单位秒
     *  @apiSuccess (bannedInfo Success 200) {Integer} overSeconds 		剩余的有效时间 单位秒
     * @apiSuccess (bannedInfo Success 200) {Integer} nickName 		主播或者管理员
     * @apiSuccess (bannedInfo Success 200) {String} message 		提示信息
     * @apiSuccess (bannedInfo Success 200)  {Integer} role	角色类型 1 主播 2 管理员
     */
    public IsChargedResponseVo isCharged(Integer userId, Integer roomId) throws ServiceException;


    /**
     * @apiVersion 1.0.0
     * @apiGroup room
     * @apiPermission Android/IOS
     * @api {POST} /room/setRoomBackgroundImg?token=xxxxxx  1.设置房间直播封面
     * @apiName setRoomBackgroundImg
     * @apiDescription > 进入主播开播界面，设置直播房间封面</br>
     *
     * @apiParam {byte[]} roomBackgroundImg  房间背景图
     * @apiSuccess {String} imgUrl 	上传地址
     */
    public String setRoomBackgroundImg(Integer userId, FileItemModel fileImg) throws ServiceException;

    /**
     * @apiVersion 1.0.0
     * @apiGroup room
     * @apiPermission Android/IOS
     * @api {GET} /room/createRoom?token=xxxxxx  2.创建房间
     * @apiName createRoom
     * @apiDescription >创建房间，如果有，返回原来的房间设置的属性</br>
     *
     *
     * @apiSuccess {Integer} roomId  房间ID
     * @apiSuccess {String} roomTitle 房间标题
     * @apiSuccess {String} roomBgPic 房间封面
     * @apiSuccess {Integer} roomStatus 房间状态
     * @apiSuccess {String} result 提示信息
     */
    public RoomInfoResponseVo createRoom(Integer userId) throws ServiceException;

}
