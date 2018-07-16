package com.eason.api.zb;

import com.eason.api.exception.ServiceException;
import com.eason.api.zb.vo.live.ZbRoomLiveVo;

import java.util.List;

/**
 * @apiDefine socket Socket即时通讯API
 */
public interface ISocketUrlService {
    /**
     * @apiVersion 1.0.0
     * @apiGroup socket
     * @apiPermission Android/IOS
     * @api {GET} /socket/getUrl 1.获取Socket地址
     * @apiName getUrl
     *
     *
     * @apiDescription
     * > 根据集群关系，获取对应的Socket地址</br>
     *
     * @apiSuccess {String} pushUrl  获取推流地址
     */
    public String getUrl(String roomId) throws ServiceException;


}
