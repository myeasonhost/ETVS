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
     * @apiDescription >如果没有房间，创建房间，如果有房间，返回原来的房间设置的属性</br>
     *
     *
     * @apiSuccess {Integer} roomId  房间ID
     * @apiSuccess {String} roomTitle 房间标题
     * @apiSuccess {String} roomBgPic 房间封面
     * @apiSuccess {Integer} roomStatus 房间状态
     * @apiSuccess {String} result 提示信息
     */
    public RoomInfoResponseVo createRoom(Integer userId) throws ServiceException;

    /**
     * @apiVersion 1.0.0
     * @apiGroup room
     * @apiPermission Android/IOS
     * @api {GET} /room/{roomId}/enterRoom 3.进入房间
     * @apiName enterRoom
     * @apiDescription > 用户与主播，进入房间，获取房间详情明细，包含如下几个动作：</br>
     * > （1）获取房间详情</br>
     * > （2）获取主播用户详情</br>
     * > （3）获取粉丝用户列表</br>
     * > （4）获取IM/Video服务器地址</br>
     * @apiSuccess {Integer} planId 		房间场次ID
     * @apiSuccess {Integer} roomId 		房间ID
     * @apiSuccess {String} roomTitle  	房间标题
     * @apiSuccess {Integer} onlineUser  房间当前在线用户
     * @apiSuccess {Integer} machineUser 房间机器人用户
     * @apiSuccess {String} roomBackgroundImg  房间背景图片
     * @apiSuccess {Integer} userId 		主播的用户ID
     * @apiSuccess {String} zbNickname	主播昵称
     * @apiSuccess {Integer} zbLevel		主播等级
     * @apiSuccess {String} zbHeadImg 	主播头像
     * @apiSuccess {String} zbSignature 主播个性签名
     * @apiSuccess {String} playUrl   拉流地址
     * @apiSuccess {String} imUrl   聊天室地址
     */
    public RoomResponseVo enterRoom(Integer userId, Integer roomId) throws ServiceException;

}
