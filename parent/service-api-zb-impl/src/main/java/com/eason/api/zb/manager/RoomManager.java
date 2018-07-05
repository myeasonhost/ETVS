package com.eason.api.zb.manager;

import com.eason.api.zb.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class RoomManager {
    private static Logger logger = LoggerFactory.getLogger(RoomManager.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate4;


    /**
     * 都是以字符串形式存储
     * admin_banned_[UID] //管理员所有房间禁止指定用户入内   存入数据为 10分钟 20分钟 或者-1永久 以秒为单位
     * admin_banned_[ROOMID]:[UID] //管理员禁止指定用户此房间入内 存入数据为 10分钟 20分钟 或者-1永久 以秒为单位
     * banned_room_user:[roomid]:[uid] //主播禁止此用户进入此房间 存入数据为 10分钟 20分钟 以秒为单位
     * message	String  提示信息
     * data	Object  返回内容
     * role	Integer 角色类型 1 主播 2 管理员
     * nickname	String  setSeconds	Integer 设置的有效时间 ；-1永久被管理员禁言 其他是固定时间，0 未被禁足 单位秒
     * overSeconds	Integer 剩余的有效时间 setSeconds -1时 此值为0；
     * @return
     * @throws ServiceException
     */
    public HashMap<String,Object> bannedUser(Integer userId, Integer roomId) throws ServiceException {
        try {
            HashMap<String,Object> hashMap=new HashMap<>();
            String adminBannedAllRoom = stringRedisTemplate4.opsForValue().get("admin_banned_"+userId);
            if (adminBannedAllRoom!=null){
                Long expireTime=stringRedisTemplate4.getExpire("admin_banned_"+userId, TimeUnit.SECONDS);
                hashMap.put("message","管理员禁止该用户进入所有房间");
                hashMap.put("role",2);   //角色类型 1 主播 2 管理员
                hashMap.put("nickName","管理员");
                hashMap.put("setSeconds", Integer.parseInt(adminBannedAllRoom));
                hashMap.put("overSeconds",expireTime);
                return  hashMap;
            }
            String adminBannedRoomId = stringRedisTemplate4.opsForValue().get("admin_banned_"+roomId+":"+userId);
            if (adminBannedRoomId!=null){
                Long expireTime=stringRedisTemplate4.getExpire("admin_banned_"+roomId+":"+userId, TimeUnit.SECONDS);
                hashMap.put("message","管理员禁止该用户进入这个房间");
                hashMap.put("roleInteger",2);   //角色类型 1 主播 2 管理员
                hashMap.put("nickName","管理员");
                hashMap.put("setSeconds", Integer.parseInt(adminBannedRoomId));
                hashMap.put("overSeconds",expireTime);
                return  hashMap;
            }
            String adminBanneduserId = stringRedisTemplate4.opsForValue().get("banned_room_user:"+roomId+":"+userId);
            if (adminBanneduserId!=null){
                Long expireTime=stringRedisTemplate4.getExpire("banned_room_user:"+roomId+":"+userId, TimeUnit.SECONDS);
                hashMap.put("message","主播禁止该用户进入这个房间");
                hashMap.put("roleInteger",1);   //角色类型 1 主播 2 管理员
                hashMap.put("nickName","主播");
                hashMap.put("setSeconds", Integer.parseInt(adminBanneduserId));
                hashMap.put("overSeconds",expireTime);
                return  hashMap;
            }

            return null;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


}
