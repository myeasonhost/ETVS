package com.eason.api.zb.service.impl;

import com.eason.api.common.util.AESOperator;
import com.eason.api.common.util.SymmetricEncoder;
import com.eason.api.zb.IIndexService;
import com.eason.api.zb.IPlatformService;
import com.eason.api.zb.IZhuboService;
import com.eason.api.zb.cache.ZbTRoomConf;
import com.eason.api.zb.cache.ZbTRoomPlan;
import com.eason.api.zb.cache.ZbTUserPersonal;
import com.eason.api.zb.dao.*;
import com.eason.api.zb.exception.ServiceException;
import com.eason.api.zb.manager.PlatformManager;
import com.eason.api.zb.model.PageModel;
import com.eason.api.zb.model.ZbConstant;
import com.eason.api.zb.po.*;
import com.eason.api.zb.vo.index.IndexResponseVo;
import com.eason.api.zb.vo.platform.IMResponseVo;
import com.eason.api.zb.vo.platform.MediaResponseVo;
import com.eason.api.zb.vo.room.RoomStatResponseVo;
import com.eason.api.zb.vo.user.UserLevelRankResponseVo;
import com.eason.api.zb.vo.user.UserResponseVo;
import com.eason.api.zb.vo.zhubo.ReadyPlayResponseVo;
import com.eason.api.zb.vo.zhubo.StartPlayRequestVo;
import com.eason.api.zb.vo.zhubo.StartPlayResponseVo;
import com.eason.api.zb.vo.zhubo.ZhuboResponseVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/zhubo")
public class ZhuboServiceImpl implements IZhuboService {
    private static Logger logger = LoggerFactory.getLogger(ZhuboServiceImpl.class);
    @Autowired
    private RoomPlanDao roomPlanDao;
    @Autowired
    private RoomTypeSetDao roomTypeSetDao;
    @Autowired
    private RoomTypePriceDao roomTypePriceDao;
    @Autowired
    private RoomConfDao roomConfDao;
    @Autowired
    private RoomPlanStatDao roomPlanStatDao;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private ZhuboDao zhuboDao;
    @Autowired
    private UcUserDao ucUserDao;
    @Autowired
    private QconfigDao qconfigDao;
    @Autowired
    private UserAttentionDao userAttentionDao;
    @Autowired
    private UserPersonalDao userPersonalDao;
    @Autowired
    private IIndexService indexServiceImpl;
    @Autowired
    private IPlatformService platformServiceImpl;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private PlatformManager platformManager;

    /**
     * 主播API - 主播列表（主播推荐）
     * 点击收藏，没有关注列表，显示热门推荐主播
     * （1）如果没有登陆，显示热门推荐主播
     * （2）如果有登陆，没有主播关注的列表，显示热门推荐的主播，如果有关联主播，在调index列表去拿关注的主播房间
     *
     * @param userId
     * @param num
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/getZhuboList/{num}", method = RequestMethod.GET)
    @Override
    public List<ZhuboResponseVo> getZhuboList(Integer userId, @PathVariable(value = "num") Integer num) throws ServiceException {
        List<ZhuboResponseVo> list = new ArrayList<>();
        try {
            //（1）如果没有登陆，显示热门推荐主播
            if (userId == null) { //未登陆
                PageModel<IndexResponseVo> pageModel = indexServiceImpl.getIndexList(userId, "1", 0, num);
                pageModel.getRows().forEach(indexResponseVo -> {
                    ZhuboResponseVo responseVo = new ZhuboResponseVo();
                    responseVo.setZbId(indexResponseVo.getZbId());
                    responseVo.setZbNickname(indexResponseVo.getZbNickName());
                    responseVo.setZbLevel(indexResponseVo.getZbLevel());
                    responseVo.setZbHeadImg(indexResponseVo.getZbHeadImg());
                    responseVo.setIsAttention(0); //0 =未关注，1=已关注
                    ZbTRoomPlan zbTRoomPlan=this.roomPlanDao.findByZbId(indexResponseVo.getZbId());
                    if (zbTRoomPlan!=null){
                        responseVo.setZbSignature(zbTRoomPlan.getZbSignature());
                        responseVo.setUserId(zbTRoomPlan.getUserId());
                        responseVo.setZbBackgroundImg(zbTRoomPlan.getRoomBgPic());
                        int attentionUserTotal = this.userAttentionDao.findATotalByFId(zbTRoomPlan.getUserId());
                        responseVo.setAttentionUserTotal(attentionUserTotal);
                        //TODO  主播列表收礼与消费总数
                        responseVo.setDiamondGiftZBTotal(this.zhuboDao.getDiamondGiftZBTotal(zbTRoomPlan.getUserId()));
                        responseVo.setCostTotal(this.zhuboDao.getCostTotal(zbTRoomPlan.getUserId()));
                        list.add(responseVo);
                    }
                });
            } else { //已登陆，拿已关注的列表
                //（2）如果有登陆，没有主播关注的列表，显示热门推荐的主播，如果有关联主播，在调index列表去拿关注的主播房间
                    PageModel<IndexResponseVo> pageModel = indexServiceImpl.getIndexList(userId, "1", 0, num);
                    pageModel.getRows().forEach(indexResponseVo -> {
                        ZhuboResponseVo responseVo = new ZhuboResponseVo();
                        responseVo.setZbId(indexResponseVo.getZbId());
                        responseVo.setZbNickname(indexResponseVo.getZbNickName());
                        responseVo.setZbLevel(indexResponseVo.getZbLevel());
                        responseVo.setZbHeadImg(indexResponseVo.getZbHeadImg());
                        responseVo.setIsAttention(0); //0 =未关注，1=已关注
                        ZbTRoomPlan zbTRoomPlan=this.roomPlanDao.findByZbId(indexResponseVo.getZbId());
                        if (zbTRoomPlan!=null){
                            responseVo.setZbSignature(zbTRoomPlan.getZbSignature());
                            responseVo.setUserId(zbTRoomPlan.getUserId());
                            responseVo.setZbBackgroundImg(zbTRoomPlan.getRoomBgPic());
                            int attentionUserTotal = this.userAttentionDao.findATotalByFId(zbTRoomPlan.getUserId());
                            responseVo.setAttentionUserTotal(attentionUserTotal);
                            //TODO  主播列表收礼与消费总数
                            responseVo.setDiamondGiftZBTotal(this.zhuboDao.getDiamondGiftZBTotal(zbTRoomPlan.getUserId()));
                            responseVo.setCostTotal(this.zhuboDao.getCostTotal(zbTRoomPlan.getUserId()));
                            list.add(responseVo);
                        }
                    });
            }

        } catch (ServiceException e) {
            throw new ServiceException(e);
        }

        return list;
    }

    @RequestMapping(value = "/getZbDetail/{zbId}", method = RequestMethod.GET)
    @Override
    public ZhuboResponseVo getZbDetail(Integer userId, @PathVariable(value = "zbId") Integer zbId) throws ServiceException {
        ZbTZhubo zbTZhubo = this.zhuboDao.getOne(zbId);
        if (zbTZhubo == null) {
            throw new ServiceException("主播不存在");
        }
        ZbTRoomPlan zbTRoomPlan = this.roomPlanDao.findByZbId(zbId);
        if (zbTRoomPlan == null) {
            throw new ServiceException("主播并未开播");
        }
        ZhuboResponseVo responseVo = new ZhuboResponseVo();
        responseVo.setZbId(zbTRoomPlan.getZbId());
        responseVo.setUserId(zbTRoomPlan.getUserId());
        responseVo.setZbNickname(zbTRoomPlan.getZbNickname());
        responseVo.setZbLevel(zbTRoomPlan.getZbLevel());
        ZbUcUser zbUcUser=ucUserDao.findOne(zbTZhubo.getUserId());
        responseVo.setZbUserLevel(zbUcUser.getLevel()*1);
        if (new Date().compareTo(zbUcUser.getExVipTime())>=0){
            responseVo.setZbUserVIP(0);
        }else{
            responseVo.setZbUserVIP(zbUcUser.getVip()*1);
        }
        responseVo.setZbHeadImg(zbTRoomPlan.getZbHeadImg());
        int count = userAttentionDao.findByAIdAndFId(userId, zbTRoomPlan.getUserId());
        if (count == 0) {
            responseVo.setIsAttention(0);   //0=未关注
        } else {
            responseVo.setIsAttention(1);   //1=已关注
        }
        ZbTUserPersonal zbTUserPersonal=userPersonalDao.findByUserIdAndZbId(userId,zbId);
        if ((zbTUserPersonal==null)){
            responseVo.setIsBook(0);    //0=未预约
        }else{
            responseVo.setIsBook(1);    //1=已关注
        }
        responseVo.setZbSignature(zbTRoomPlan.getZbSignature());
        responseVo.setZbBackgroundImg(zbTRoomPlan.getRoomBgPic());
        int attentionUserTotal = this.userAttentionDao.findATotalByFId(zbTRoomPlan.getUserId());
        responseVo.setAttentionUserTotal(attentionUserTotal);
        //TODO  主播列表收礼与消费总数
        responseVo.setDiamondGiftZBTotal(this.zhuboDao.getDiamondGiftZBTotal(zbTRoomPlan.getUserId()));
        responseVo.setCostTotal(this.zhuboDao.getCostTotal(zbTRoomPlan.getUserId()));
        return responseVo;
    }

    @RequestMapping(value = "/{zbId}/getAttentionUserList", method = RequestMethod.GET)
    @Override
    public List<UserLevelRankResponseVo> getAttentionUserList(@PathVariable(value = "zbId") Integer zbId) {
        List<UserLevelRankResponseVo> userLevelRankList = new ArrayList<>();
        UserLevelRankResponseVo userLevelRankResponseVo = new UserLevelRankResponseVo();
        userLevelRankResponseVo.setUserId(1);
        userLevelRankResponseVo.setNickName("测试用户01");
        userLevelRankResponseVo.setSex(1);
        userLevelRankResponseVo.setUserHeadImg("http://userHeadImg");
        userLevelRankResponseVo.setUserLevel(3);
        userLevelRankList.add(userLevelRankResponseVo);
        return userLevelRankList;
    }

    @RequestMapping(value = "/{zbId}/getGiftUserList/{category}", method = RequestMethod.GET)
    @Override
    public List<UserResponseVo> getGiftUserList(Integer zbId, String category) {
        List<UserResponseVo> diamondRankList = new ArrayList<>();
        UserResponseVo userResponseVo = new UserResponseVo();
        userResponseVo.setUserId(1);
        userResponseVo.setNickName("测试用户01");
        userResponseVo.setSex(1);
        userResponseVo.setUserHeadImg("http://userHeadImg");
        userResponseVo.setUserLevel(3);
//        userResponseVo.setDiamondBalance(200.2);
//        userResponseVo.setGiftRankNo1(2);
        userResponseVo.setDiamondGiftUserTotal(1333);
        diamondRankList.add(userResponseVo);
        return diamondRankList;
    }

    /**
     * 主播API - 获取开播信息
     * 进入主播开播界面业务流程
     * （1）验证参数：是否合法
     * （2）获取IM与Madia地址：
     * A.如果拿不到地址，IM=null或者Media=null接口正常返回，不中断
     * B.如果拿到地址，存入缓存zb_t_room_conf，用户看直播的时候直接重新拿推荐地址，一个地址绑定一个主播
     * （3）验证房间状态：
     * A.未开播（=2）继续（3-7）
     * B.直播中（=1）直接进入直播间，返回上一次的房间配置信息
     * //C.回访中（=3）UI弹出对话框是否结束回放？？？如果是结束回放，更新房间状态3（回访中）-2（未开播）重新设置房间，UI重启API，继续（1-7）？？？待确定？？？
     * （4）获取主播权限：
     * A.判断主播是否被禁播；UI弹出提示框
     * B.判断是否拥有 时常房间、门票房间、私密房间、游戏房间的开播权限
     * （5）获取房间属性：
     * A.查库—动态配置开播时间、持续时间、门票价格数据等
     * B.配置UI—根据允许的类型从后台拉对应的房间配置数据，动态配置开播时间、持续时间、门票价格UI显示
     * （6）维护表：qvod_zb_t_room
     * A.如果无房间，创建房间，status=0（创建中）初始化房间zbId,status,createTime
     * B.如果有房间，
     * (a)房间未直播，更新房间，status=2（未开播）-0（创建中）
     * (b)房间在直播，直接返回，status=1（直播中）
     * <p>
     * （7）组建返回值：
     * A.如果有房间，返回上一次的房间配置信息（roomId,roomTitle,status,roomBackgroundImg），返回开房权限属性配置组装UI（Map）
     * B.如果无房间，返回当前的房间配置信息（roomId,status,roomTitle=null,roomBackgroundImg=null），返回开房权限属性配置组装UI（Map）
     *
     * @param userId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/getReadyPlayInfo", method = RequestMethod.GET)
    @Override
    public ReadyPlayResponseVo getReadyPlayInfo(Integer userId, String token) throws ServiceException {
        //（1）验证参数：是否合法
        ZbTZhubo zbTZhubo = this.zhuboDao.findByUserId(userId);
        if (zbTZhubo == null || zbTZhubo.getZbId() == null) {
            throw new ServiceException("主播不存在，请您先申请主播");
        }
        Integer zbId = zbTZhubo.getZbId();
        ReadyPlayResponseVo responseVo = new ReadyPlayResponseVo();
        ZbTQvodConfigs config=this.qconfigDao.findByConfig("download_url");
        if (config==null){
            responseVo.setDownload_url("无法获取download_url地址");
        }else{
            responseVo.setDownload_url(config.getDescription());
        }
        //（2）获取IM与Madia地址：直播房间在，则不用清楚缓存，如果不存在，需要清楚地址缓存
        ZbTRoomConf zbTRoomConf = this.roomConfDao.findByZbId(zbId);
        ZbTRoomPlan zbTRoomPlan = this.roomPlanDao.findByZbId(zbId);
        if (zbTRoomPlan==null){
            if (zbTRoomConf!=null){
                this.roomConfDao.delete(zbTRoomConf);
            }
        }
        if (zbTRoomConf == null) {
            zbTRoomConf = new ZbTRoomConf();
            zbTRoomConf.setZbId(zbId);
            zbTRoomConf.setUserId(userId);
        }
        IMResponseVo imResponseVo = null;
        if (zbTRoomConf.getImInfo()==null){
            try {
                String im_access_token=platformManager.getImAccessToken();
                Map<String,Object> imMap=platformManager.getImUrl(zbId,im_access_token,token);
                if (imMap!=null){
                    imResponseVo=new IMResponseVo("1", (String) imMap.get("ip"),(Integer) imMap.get("port"), im_access_token);
                    zbTRoomConf.setImInfo(imResponseVo);
                }else {
                    throw new ServiceException("无法获取IM服务器地址");
                }
            } catch (Exception e) {
                logger.error("getReadyPlayInfo--IM="+e.getMessage());
            }
        }

        IMResponseVo imResponseVo1=null;
        if (zbTRoomConf.getImInfo()!=null){
            imResponseVo1=new IMResponseVo();
            imResponseVo1.setType(zbTRoomConf.getImInfo().getType());
            imResponseVo1.setAccess_token(zbTRoomConf.getImInfo().getAccess_token());
            imResponseVo1.setPort(zbTRoomConf.getImInfo().getPort());
            imResponseVo1.setUrl(AESOperator.encrypt(zbTRoomConf.getImInfo().getUrl()));
        }
        responseVo.setIm(imResponseVo1);


        //（4）获取主播权限：
        Integer status = zbTZhubo.getZbStatus();
        if (ZbConstant.ZB.status.disable == status) {
            throw new ServiceException("直播已经被禁播，请联系房管");
        }
        Integer isTicket=zbTZhubo.getTicketState();
        Integer isTime=zbTZhubo.getTimeState();
        Integer isPersonal=zbTZhubo.getPersonalState();
        Integer isGame=zbTZhubo.getGameState();
        //（5）获取房间属性：
        List<Object[]> list1=roomTypePriceDao.listByRoomPriceList(ZbConstant.Room.Type.ticket.name());
        String activityTimeList1=null;
        String priceList1=null;
        if (list1.size()==1){
            activityTimeList1=(String) list1.get(0)[1];
            priceList1=(String) list1.get(0)[2];
        }
        Map<String, Object> ticketConf = new HashMap<>();
        ticketConf.put("startTime", new Timestamp(System.currentTimeMillis()));//当前类型直播开始的时间
        ticketConf.put("activityTimeList",activityTimeList1);
        ticketConf.put("priceList", priceList1);
        if (isTicket==1){
            responseVo.setTicketConf(ticketConf);
        }else {
            responseVo.setTicketConf(new HashMap<>());
        }
        List<Object[]> list2=roomTypePriceDao.listByRoomPriceList(ZbConstant.Room.Type.time.name());
        String activityTimeList2=null;
        String priceList2=null;
        String timeInterval=null;
        if (list2.size()==1){
            activityTimeList2=(String) list2.get(0)[1];
            priceList2=(String) list2.get(0)[2];
            timeInterval=(String) list2.get(0)[3];
        }

        Map<String, Object> timeConf = new HashMap<>();
        timeConf.put("startTime", new Timestamp(System.currentTimeMillis()));//当前类型直播开始的时间
        timeConf.put("activityTimeList", activityTimeList2);
        timeConf.put("priceList", priceList2);
        timeConf.put("timeInterval", timeInterval);
        if (isTime==1){
            responseVo.setTimeConf(timeConf);
        }else {
            responseVo.setTimeConf(new HashMap<>());
        }

        List<Object[]> list3=roomTypePriceDao.listByRoomPriceList(ZbConstant.Room.Type.personal.name());
        String activityTimeList3=null;
        if (list3.size()==1){
            activityTimeList3=(String) list3.get(0)[1];
        }

        Map<String, Object> personalConf = new HashMap<>();
        personalConf.put("startTime", new Timestamp(System.currentTimeMillis()));//当前类型直播开始的时间
        personalConf.put("activityTimeList", activityTimeList3);
        if (isPersonal==1){
            responseVo.setPersonalConf(personalConf);
        }else {
            responseVo.setPersonalConf(new HashMap<>());
        }
        if (isGame==1){
            responseVo.setGameConf(new HashMap<>());
        }else {
            responseVo.setGameConf(new HashMap<>());
        }
    //（3）验证房间状态：续播也需要获取权限ID，放到第5步之后
        if (zbTRoomPlan != null && ZbConstant.Room.status.room_ing == zbTRoomPlan.getRoomStatus()) {
            responseVo.setRoomId(zbTRoomPlan.getRoomId());
            responseVo.setRoomStatus(zbTRoomPlan.getRoomStatus());
            responseVo.setRoomTitle(zbTRoomPlan.getRoomTitle());
            responseVo.setRoomBackgroundImg(zbTRoomPlan.getRoomBgPic());
            responseVo.setRoomType(zbTRoomPlan.getRoomType());
            responseVo.setOpenTime(new Timestamp(zbTRoomPlan.getOpenTime().getTime()));
            responseVo.setResult("房间正在直播中，直播重新进入房间");
            //(2B)如果拿到地址，存入缓存，用户看直播的时候直接重新拿推荐地址，一个地址绑定一个主播
            zbTRoomConf.setRoomId(zbTRoomPlan.getRoomId());
            this.roomConfDao.save(zbTRoomConf);
            //3B.直播中（=1）直接进入直播间，返回上一次的房间配置信息，新加remainTime剩余时间字段
            if (ZbConstant.Room.Type.ticket.name().equals(zbTRoomPlan.getRoomType())) {
                Map<String, Object> map = zbTRoomPlan.getRoomSet();
                Integer selectActivityTime = (Integer) zbTRoomPlan.getRoomSet().get("selectActivityTime");
                Date overTime = (Date) zbTRoomPlan.getRoomSet().get("overTime");
                Date now = new Date();
                long t = overTime.getTime() - now.getTime();
                long remainTime = 0;
                if (t > 0) {
                    remainTime = t;  //1分钟=60000
                    if (t > selectActivityTime * 60000) {
                        remainTime = selectActivityTime * 60000;
                    }
                }
                map.put("remainTime", remainTime);
                responseVo.setTicketConf(map);
            }
            if (ZbConstant.Room.Type.time.name().equals(zbTRoomPlan.getRoomType())) {
                Map<String, Object> map = zbTRoomPlan.getRoomSet();
                Integer selectActivityTime = (Integer) zbTRoomPlan.getRoomSet().get("selectActivityTime");
                Date overTime = (Date) zbTRoomPlan.getRoomSet().get("overTime");
                Date now = new Date();
                long t = overTime.getTime() - now.getTime();
                long remainTime = 0;
                if (t > 0) {
                    remainTime = t;  //1分钟=60000
                    if (t > selectActivityTime * 60000) {
                        remainTime = selectActivityTime * 60000;
                    }
                }
                map.put("remainTime", remainTime);
                responseVo.setTimeConf(map);
            }
            if (ZbConstant.Room.Type.personal.name().equals(zbTRoomPlan.getRoomType())) {
                Map<String, Object> map = zbTRoomPlan.getRoomSet();
                Integer selectActivityTime = (Integer) zbTRoomPlan.getRoomSet().get("selectActivityTime");
                Date overTime = (Date) zbTRoomPlan.getRoomSet().get("overTime");
                Date now = new Date();
                long t = overTime.getTime() - now.getTime();
                long remainTime = 0;
                if (t > 0) {
                    remainTime = t;  //1分钟=60000
                    if (t > selectActivityTime * 60000) {
                        remainTime = selectActivityTime * 60000;
                    }
                }
                map.put("remainTime", remainTime);
                responseVo.setPersonalConf(map);
            }
            return responseVo;
        }
        if (zbTRoomPlan != null && ZbConstant.Room.status.room_redio == zbTRoomPlan.getRoomStatus()) {
            responseVo.setRoomId(zbTRoomPlan.getRoomId());
            responseVo.setRoomStatus(zbTRoomPlan.getRoomStatus());
            responseVo.setRoomTitle(zbTRoomPlan.getRoomTitle());
            responseVo.setRoomBackgroundImg(zbTRoomPlan.getRoomBgPic());
            responseVo.setResult("房间正在回放中，待UI是否结束回放");
            return responseVo;
        }
        //（6.A）维护表：如果无房间，创建房间，status=0（创建中）初始化房间zbId,status,createTime
        ZbTRoom zbTRoom = this.roomDao.findByZbId(zbId);
        if (zbTRoom == null) {
            zbTRoom = new ZbTRoom();
            zbTRoom.setZbId(zbId);
            zbTRoom.setRoomStatus(ZbConstant.Room.status.room_new);
            zbTRoom.setCreate_Time(new Timestamp(System.currentTimeMillis()));
            this.roomDao.save(zbTRoom);

            responseVo.setRoomStatus(zbTRoom.getRoomStatus());
        } else {
            ZbTRoomPlan zbTRoomPlan1 = this.roomPlanDao.findByRoomId(zbTRoom.getRoomId());
            if (zbTRoomPlan1 == null) {
                // 6B.(a)房间未直播，更新房间，status=2（未开播）-0（创建中）
                this.roomDao.updateRoomStatusAndAndRoomTitle(zbTRoom.getRoomId(), ZbConstant.Room.status.room_new, zbTRoom.getRoomTitle());
                responseVo.setRoomStatus(ZbConstant.Room.status.room_new);
            } else {
                //  6B.(b)房间在直播，直接返回，status=1（直播中）
                responseVo.setRoomType(zbTRoomPlan1.getRoomType());
                this.roomDao.updateRoomStatusAndAndRoomTitle(zbTRoom.getRoomId(), ZbConstant.Room.status.room_ing, zbTRoom.getRoomTitle());
                responseVo.setRoomStatus(ZbConstant.Room.status.room_ing);
            }

        }
        // (2B)如果拿到地址，存入缓存，用户看直播的时候直接重新拿推荐地址，一个地址绑定一个主播,存储房间推拉流地址、聊天地址等信息
        zbTRoomConf.setRoomId(zbTRoom.getRoomId());
        this.roomConfDao.save(zbTRoomConf);

        responseVo.setRoomId(zbTRoom.getRoomId());
        responseVo.setRoomTitle(zbTRoom.getRoomTitle());
        responseVo.setRoomBackgroundImg(zbTRoom.getRoomBgPic());
        responseVo.setResult("房间初始化成功");
        return responseVo;
    }

    /**
     * 主播API - 开始直播
     * 选择房间类型，点击开始直播：
     * （1）验证参数：是否合法
     * （2）验证房间状态：
     * A.未开播（=2）重新设置房间
     * B.直播中（=1）直接进入直播间，更新DB+缓存直播标题 等数据
     * //C.回访中（=3）UI弹出对话框是否结束回放？？？如果是结束回放，更新房间状态3（回访中）-2（未开播）重新设置房间，UI重启API，继续（1-7）？？？待确定？？？
     * （3）维护表：zb_t_room与zb_t_room_plan
     * A. 先更新房间表，roomTitle，status=1（直播中）
     * B.查询当前房间场次，确保缓存中没场次（用户没有直播），在进行场次的创建，场次status=房间status
     * C.创建场次，存储到缓存 房间信息，主播信息（主播昵称、等级、主播头像等），房间属性配置信息，场次信息
     * D.如果是私密房间，更新用户预约表zb_t_user_personal的邀请时间，已经房间预约信息
     * （4）开播消息推送： TODO  粉丝设置主播开播提醒表还未设计（直接调IM推送接口，又IM接口实现推送逻辑）
     * A.关注粉丝用户的消息推送，并且粉丝已开启了主播消息提醒（勾选了的主播才能进行消息推送）
     * B.对接IM的消息推送接口（多用户发送），接口异常处理，不中断
     * （5）组件返回值：
     * A.planId,status,result
     *
     * @param userId
     * @param startPlayRequestVo
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/startPlay", method = RequestMethod.POST)
    @Override
    public StartPlayResponseVo startPlay(Integer userId, @RequestBody StartPlayRequestVo startPlayRequestVo) throws ServiceException {
        //（1）验证参数：是否合法
        ZbTZhubo zbTZhubo = this.zhuboDao.findByUserId(userId);
        if (zbTZhubo == null || zbTZhubo.getZbId() == null) {
            throw new ServiceException("主播不存在，请您先申请主播");
        }
        Integer zbId = zbTZhubo.getZbId();
        ZbTRoom zbTRoom = this.roomDao.findByZbId(zbId);
        if (zbTRoom == null) {
            throw new ServiceException("主播的房间不存在");
        }

        String roomType = startPlayRequestVo.getRoomType();
        if (StringUtils.isEmpty(roomType)) {
            throw new ServiceException("房间类型不能为空");
        }
        try {
            ZbConstant.Room.Type.valueOf(roomType);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("房间类型错误");
        }
//        if (ZbConstant.Room.Type.game.name().equals(roomType)) {
//            throw new ServiceException("房间尽请期待");
//        }

        String roomTitle = startPlayRequestVo.getRoomTitle();
        if (StringUtils.isEmpty(roomTitle)) {
            throw new ServiceException("房间标题不能为空");
        }
        if (roomTitle.length() > 10) {
            throw new ServiceException("房间标题最多10个汉字");
        }
        if (ZbConstant.Room.Type.ticket.name().equals(roomType) || ZbConstant.Room.Type.time.name().equals(roomType)) {
            if (startPlayRequestVo.getStartTime() == null) {
                throw new ServiceException("开始时间不能为空");
            }
            Integer activityTime = startPlayRequestVo.getActivityTime();
            if (activityTime == null) {
                throw new ServiceException("持续时间不能为空");
            }
            Integer price = startPlayRequestVo.getPrice();
            if (price == null) {
                throw new ServiceException("单价不能为空");
            }
        }
        ZbTUserPersonal zbTUserPersonal = null;
        if (ZbConstant.Room.Type.personal.name().equals(roomType)) {
            if (startPlayRequestVo.getStartTime() == null) {
                throw new ServiceException("开始时间不能为空或者格式错误，请传Long类型的时间戳");
            }
            Integer activityTime = startPlayRequestVo.getActivityTime();
            if (activityTime == null) {
                throw new ServiceException("持续时间不能为空");
            }
            Integer userClientId = startPlayRequestVo.getUserId();
            if (userClientId == null) {
                throw new ServiceException("贵宾用户不能为空");
            }
            zbTUserPersonal = this.userPersonalDao.findByUserIdAndZbId(userClientId, zbId);
            if (zbTUserPersonal == null) {
                throw new ServiceException("贵宾用户可能没有提前预约主播");
            }
        }
        //（2）验证房间状态：
        ZbTRoomPlan zbTRoomPlan = this.roomPlanDao.findByRoomId(zbTRoom.getRoomId());
        if (ZbConstant.Room.status.room_ing == zbTRoom.getRoomStatus() && zbTRoomPlan != null) {
            StartPlayResponseVo startPlayResponseVo = new StartPlayResponseVo();
            startPlayResponseVo.setPlanId(zbTRoomPlan.getPlanId());
            startPlayResponseVo.setRoomId(zbTRoomPlan.getRoomId());
            startPlayResponseVo.setRoomStatus(zbTRoom.getRoomStatus());
            startPlayResponseVo.setResult("房间正在直播中，直播重新进入房间");
            //2A. 更新数据库+缓存标题（只能更新标题）
            this.roomDao.updateRoomStatusAndAndRoomTitle(zbTRoomPlan.getRoomId(), zbTRoom.getRoomStatus(), roomTitle);
            zbTRoomPlan.setRoomStatus(ZbConstant.Room.status.room_ing);
            zbTRoomPlan.setRoomTitle(roomTitle);
            this.roomPlanDao.save(zbTRoomPlan);
            return startPlayResponseVo;
        }
        if (ZbConstant.Room.status.room_redio == zbTRoom.getRoomStatus()) {
            StartPlayResponseVo startPlayResponseVo = new StartPlayResponseVo();
            startPlayResponseVo.setPlanId(zbTRoomPlan.getPlanId());
            startPlayResponseVo.setRoomId(zbTRoomPlan.getRoomId());
            startPlayResponseVo.setRoomStatus(zbTRoom.getRoomStatus());
            startPlayResponseVo.setResult("房间正在回放中，待UI是否结束回放");

            return startPlayResponseVo;
        }
        zbTRoomPlan = new ZbTRoomPlan();
        //(3.) 更新房间信息
        this.roomDao.updateRoomStatusAndAndRoomTitle(zbTRoom.getRoomId(), ZbConstant.Room.status.room_ing, startPlayRequestVo.getRoomTitle());
        zbTRoomPlan.setRoomTitle(startPlayRequestVo.getRoomTitle());
        //(3.B)维护表：zb_t_room与zb_t_room_plan B.创建场次，存储到缓存 房间信息，主播信息（主播昵称、等级、主播头像等），房间属性配置信息，场次信息

//        zbTRoomPlan.setPlanId(zbTRoom.getRoomId()); 主键自增
        zbTRoomPlan.setRoomId(zbTRoom.getRoomId());
        zbTRoomPlan.setOrderField(zbTRoom.getOrderField());

        zbTRoomPlan.setRoomType(startPlayRequestVo.getRoomType());
        zbTRoomPlan.setRoomStatus(ZbConstant.Room.status.room_ing);     //同步记录房间状态
        zbTRoomPlan.setRoomBgPic(zbTRoom.getRoomBgPic());
        zbTRoomPlan.setOpenTime(new Date());    //开播时间

        zbTRoomPlan.setZbId(zbId);
        zbTRoomPlan.setUserId(userId);

        zbTRoomPlan.setBombScreen_count(0);
        zbTRoomPlan.setGiftCount(0);
        zbTRoomPlan.setIncomeAmountToday(0);
        zbTRoomPlan.setIncomeAmount(0);
        zbTRoomPlan.setMachineUser(0);
        zbTRoomPlan.setOnlineUser(0);
        zbTRoomPlan.setViewCount(0);
        zbTRoomPlan.setRoom_No1(0);
        zbTRoomPlan.setActivityTimeCount(0);

        //TODO 需要Qvod系统拿用户昵称、等级、主播头像
//        String userDetail = (String) stringRedisTemplate.opsForHash().get("user_detail_info", userId + "");
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> resultMap = null;
//        try {
//            resultMap = objectMapper.readValue(userDetail, Map.class);
//        } catch (IOException e) {
//            e.getMessage();
//        }
//        String nickName = (String) resultMap.get("nickname");
//        String avatar = (String) resultMap.get("avatar");
//        Integer level = zbTZhubo.getVipLevel(); //不是主播用户的等级，是主播的等级
//        String signature = (String) resultMap.get("signature");
        ZbUcUser ucUser=this.ucUserDao.findOne(userId);
        zbTRoomPlan.setZbNickname(ucUser.getNickname());
        zbTRoomPlan.setZbLevel(ucUser.getLevel()*1);
        zbTRoomPlan.setZbHeadImg(ucUser.getAvatar());
        zbTRoomPlan.setZbSignature(ucUser.getSignature());
        //(3)创建房间配置信息，先获取配置信息，一并存入
        if (ZbConstant.Room.Type.ticket.name().equals(roomType)) {
            List<Object[]> list1=roomTypePriceDao.listByRoomPriceList(ZbConstant.Room.Type.ticket.name());
            ZbTQvodZbTRoomTypePrice roomTypePrice=roomTypePriceDao.findByTypeAndAndTime(ZbConstant.Room.Type.ticket.name(),startPlayRequestVo.getActivityTime());
            if (roomTypePrice==null){
                throw new ServiceException("门票时间参数有误");
            }

            Set<Integer> ticketPR = new TreeSet<>();
            String[] temp=roomTypePrice.getPrice().split(",");
            Integer[] result = new Integer[temp.length];//int类型数组
            for(int i=0;i<temp.length;i++) {
                result[i] = Integer.parseInt(temp[i]);//整数数组
            }
            ticketPR.addAll(Arrays.asList(result));
            if (!ticketPR.contains(startPlayRequestVo.getPrice())) {
                throw new ServiceException("门票价格参数有误");
            }

            Map<String, Object> map = new HashMap<>();
            map.put("startTime", new Date(startPlayRequestVo.getStartTime()));//当前类型直播开始的时间
            map.put("activityTimeList", list1.get(0)[1]);
            map.put("selectActivityTime", startPlayRequestVo.getActivityTime());
            map.put("overTime", DateUtils.addMinutes(new Date(startPlayRequestVo.getStartTime()), startPlayRequestVo.getActivityTime()));
            map.put("priceList", StringUtils.join(ticketPR, ","));
            map.put("selectPrice", startPlayRequestVo.getPrice());
            zbTRoomPlan.setRoomSet(map);  //门票收费或者时长收费
        }

        if (ZbConstant.Room.Type.time.name().equals(roomType)) {
            List<Object[]> list2=roomTypePriceDao.listByRoomPriceList(ZbConstant.Room.Type.time.name());
            ZbTQvodZbTRoomTypePrice roomTypePrice=roomTypePriceDao.findByTypeAndAndTime(ZbConstant.Room.Type.time.name(),startPlayRequestVo.getActivityTime());
            if (roomTypePrice==null){
                throw new ServiceException("时长时间参数有误");
            }

            Set<Integer> timePR = new TreeSet<>();
            String[] temp=roomTypePrice.getPrice().split(",");
            Integer[] result = new Integer[temp.length];//int类型数组
            for(int i=0;i<temp.length;i++) {
                result[i] = Integer.parseInt(temp[i]);//整数数组
            }
            timePR.addAll(Arrays.asList(result));
            if (!timePR.contains(startPlayRequestVo.getPrice())) {
                throw new ServiceException("时长价格参数有误");
            }

            Map<String, Object> map = new HashMap<>();
            map.put("startTime", new Date(startPlayRequestVo.getStartTime()));//当前类型直播开始的时间
            map.put("activityTimeList", list2.get(0)[1]);
            map.put("selectActivityTime", startPlayRequestVo.getActivityTime());
            map.put("overTime", DateUtils.addMinutes(new Date(startPlayRequestVo.getStartTime()), startPlayRequestVo.getActivityTime()));
            map.put("priceList", StringUtils.join(timePR, ","));
            map.put("selectPrice", startPlayRequestVo.getPrice());
            map.put("timeInterval", roomTypePrice.getTimeInterval());
            zbTRoomPlan.setRoomSet(map);  //门票收费或者时长收费
        }

        if (ZbConstant.Room.Type.personal.name().equals(roomType)) {
            List<Object[]> list3=roomTypePriceDao.listByRoomPriceList(ZbConstant.Room.Type.personal.name());
            ZbTQvodZbTRoomTypePrice roomTypePrice=roomTypePriceDao.findByTypeAndAndTime(ZbConstant.Room.Type.personal.name(),startPlayRequestVo.getActivityTime());
            if (roomTypePrice==null){
                throw new ServiceException("私人房间时间参数有误");
            }

            Set<Integer> personalPR = new TreeSet<>();
            String[] temp=roomTypePrice.getPrice().split(",");
            Integer[] result = new Integer[temp.length];//int类型数组
            for(int i=0;i<temp.length;i++) {
                result[i] = Integer.parseInt(temp[i]);//整数数组
            }
            personalPR.addAll(Arrays.asList(result));
            if (!personalPR.contains(startPlayRequestVo.getPrice())) {
                throw new ServiceException("私人房间价格参数有误");
            }

            Map<String, Object> map = new HashMap<>();
            map.put("startTime", new Date(startPlayRequestVo.getStartTime()));//当前类型直播开始的时间
            map.put("selectActivityTime", startPlayRequestVo.getActivityTime());
            map.put("activityTimeList", list3.get(0)[1]);
            map.put("overTime", DateUtils.addMinutes(new Date(startPlayRequestVo.getStartTime()), startPlayRequestVo.getActivityTime()));
            map.put("userId", startPlayRequestVo.getUserId());
            zbTRoomPlan.setRoomSet(map);  //私密收费
            //(3.D).如果是私密房间，更新用户预约表zb_t_user_personal的邀请时间，已经房间预约信息
            zbTUserPersonal.setActivityTime(startPlayRequestVo.getActivityTime());
            zbTUserPersonal.setStartTime(new Date(startPlayRequestVo.getStartTime()));
            zbTUserPersonal.setInviteTime(new Date());
            this.userPersonalDao.save(zbTUserPersonal);
        }
        this.roomPlanDao.save(zbTRoomPlan);

        //TODO （4）关注用户的消息推送，自己已关注的主播进行消息提醒，勾选了的主播才能进行消息推送

        //(5) 返回结果
        StartPlayResponseVo startPlayResponseVo = new StartPlayResponseVo();
        startPlayResponseVo.setRoomId(zbTRoom.getRoomId());
        startPlayResponseVo.setPlanId(zbTRoomPlan.getPlanId());
        startPlayResponseVo.setRoomStatus(ZbConstant.Room.status.room_ing);
        startPlayResponseVo.setResult("房间场次创建成功，主播开始直播");
        return startPlayResponseVo;
    }

    /**
     *  点击确认退出，结束直播</br>
     * （1）记录直播视频状态is_video=0</br>
     *（2）调Media接口删除视频</br>
     * @param userId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/overPlay/{planId}", method = RequestMethod.GET)
    @Override
    public String overPlay(Integer userId,@PathVariable(value = "planId") Integer planId) throws ServiceException {
        try {
            //（1）记录直播视频状态is_video=0
            ZbTRoomPlanStat zbTRoomPlanStat=this.roomPlanStatDao.findByPlanId(planId);
            if (zbTRoomPlanStat==null){
                throw new ServiceException("当前场次"+planId+"不存在");
            }
            zbTRoomPlanStat.setIsVideo(ZbConstant.Room.video.disable);
            this.roomPlanStatDao.save(zbTRoomPlanStat);
            //（2）调Media接口删除视频
//            ZbTRoomConf zbTRoomConf=this.roomConfDao.findByZbId(zbTRoomPlanStat.getZbId());
//            this.platformManager.deleteRecFile(zbTRoomConf.getZbId(),zbTRoomConf.getMediaInfo().getUrl());
            return "退出成功";
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     *
     * @param userId
     * @param planId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/saveVideo/{planId}", method = RequestMethod.GET)
    @Override
    public String saveVideo(Integer userId,@PathVariable(value = "planId") Integer planId) throws ServiceException {
        //（1）记录直播视频状态is_video=1
        ZbTRoomPlanStat zbTRoomPlanStat=this.roomPlanStatDao.findByPlanId(planId);
        if (zbTRoomPlanStat==null){
            throw new ServiceException("当前场次"+planId+"不存在");
        }
        zbTRoomPlanStat.setIsVideo(ZbConstant.Room.video.enable);
        this.roomPlanStatDao.save(zbTRoomPlanStat);
        return "保持成功";
    }


    @RequestMapping(value = "/getStat/{planId}", method = RequestMethod.GET)
    @Override
    public RoomStatResponseVo getStat(Integer userId, @PathVariable(value = "planId") Integer planId) throws ServiceException {
        try {
            ZbTRoomPlanStat zbTRoomPlanStat=this.roomPlanStatDao.findByPlanId(planId);
            if (zbTRoomPlanStat==null){
                throw new ServiceException("当前场次"+planId+"不存在");
            }
            RoomStatResponseVo roomStatResponseVo=new RoomStatResponseVo();
            roomStatResponseVo.setPlanId(zbTRoomPlanStat.getPlanId());
            roomStatResponseVo.setStatId(zbTRoomPlanStat.getRecordId());
            roomStatResponseVo.setActivityTime(zbTRoomPlanStat.getActivityTime());
            roomStatResponseVo.setBombScreenCount(zbTRoomPlanStat.getBombScreen_count());
            roomStatResponseVo.setGiftCount(zbTRoomPlanStat.getGiftCount());
            roomStatResponseVo.setIncomeAmount(zbTRoomPlanStat.getIncomeAmount());
            roomStatResponseVo.setViewCount(zbTRoomPlanStat.getViewCount());
            String conf=zbTRoomPlanStat.getZbRoomConf();
            if (StringUtils.isNotEmpty(conf)){
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> confMap = objectMapper.readValue(conf, Map.class);
                    roomStatResponseVo.setOnlineUser(confMap.get("onlineUser")!=null?(Integer) confMap.get("onlineUser"):0);
                    roomStatResponseVo.setMachineUser(confMap.get("machineUser")!=null?(Integer) confMap.get("machineUser"):0);
                    roomStatResponseVo.setWatchCount(confMap.get("watchCount")!=null?(Integer) confMap.get("watchCount"):0);
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    roomStatResponseVo.setOnlineUser(0);
                    roomStatResponseVo.setMachineUser(0);
                    roomStatResponseVo.setWatchCount(0);
                }
            }else{
                roomStatResponseVo.setOnlineUser(0);
                roomStatResponseVo.setMachineUser(0);
                roomStatResponseVo.setWatchCount(0);
            }
            Integer attentionUser=this.userAttentionDao.findATotalByFId(userId);
            roomStatResponseVo.setAttentionCount(attentionUser);
            ZbTRoom zbTRoom=this.roomDao.findByZbUserId(userId);
            roomStatResponseVo.setRoomBgPic(zbTRoom.getRoomBgPic());
            roomStatResponseVo.setRoomTitle(zbTRoom.getRoomTitle());
            return roomStatResponseVo;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    @Override
    public String apply(Integer userId) throws ServiceException {
        try {
            ZbTZhubo zbTZhubo=this.zhuboDao.findByUserId(userId);
            if (zbTZhubo!=null){
                throw new ServiceException("用户已经申请过主播了");
            }
            zbTZhubo=new ZbTZhubo();
            zbTZhubo.setUserId(userId);
            zbTZhubo.setVipLevel(0);
            zbTZhubo.setZbStatus(ZbConstant.ZB.status.enable);
            this.zhuboDao.save(zbTZhubo);
            return "申请主播成功";
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * > 主播API - 获取房间配置信息</br>
     * > 获取房间配置信息业务流程</br>
     * > （1）验证参数：是否合法</br>
     * > （2）获取主播权限：</br>
     * >              A.判断主播是否被禁播；UI弹出提示框</br>
     * >              B.判断是否拥有 时常房间、门票房间、私密房间、游戏房间的开播权限</br>
     * > （3）获取房间属性：</br>
     *  >             A.查库—动态配置开播时间、持续时间、门票价格数据等</br>
     * >              B.配置UI—根据允许的类型从后台拉对应的房间配置数据，动态配置开播时间、持续时间、门票价格UI显示</br>
     * > （4）组建返回值：</br>
     *  >             A.如果有房间，返回上一次的房间配置信息（roomId,roomTitle,status,roomBackgroundImg），返回开房权限属性配置组装UI（Map）</br>
     * >              B.如果无房间，返回当前的房间配置信息（roomId,status,roomTitle=null,roomBackgroundImg=null），返回开房权限属性配置组装UI（Map）</br>
     *
     * @apiSuccess {Integer} roomId  房间id
     * @apiSuccess {Integer=0,1,2,3}  roomStatus 房间状态(0=创建，1=直播中，2=未开播，3=回放中)
     * @apiSuccess {String="normal","ticket","time","personal","game"} roomType 	房间类型
     * @apiSuccess {String} roomTitle  房间标题=直播标题
     * @apiSuccess {String} roomBackgroundImg  房间背景图
     * @apiSuccess {String} download_url  下载地址
     * @apiSuccess {String} result  返回信息
     *
     * @apiSuccess (ticket Success 200) {String="normal","ticket","time","personal","game"} roomType 	房间类型
     * @apiSuccess (ticket Success 200) {Timestamp} startTime 开始时间
     * @apiSuccess (ticket Success 200) {String} activityTimeList   继续时间=[30,60,90,120]
     * @apiSuccess (ticket Success 200) {String} priceList   门票单价列表=[20,50,100,120]
     * @apiSuccess (ticket Success 200) {Integer} selectActivityTime   当前选择持续时间
     * @apiSuccess (ticket Success 200) {Integer} selectPrice   当前选择门票价格
     *
     * @apiSuccess (time Success 200) {String="normal","ticket","time","personal","game"} roomType 	房间类型
     * @apiSuccess (time Success 200) {Timestamp} startTime 开始时间
     * @apiSuccess (time Success 200) {String} activityTimeList   继续时间=[30,60,90,120]
     * @apiSuccess (time Success 200) {String} priceList   每分钟单价列表=[1,2,5,10]
     * @apiSuccess (time Success 200) {Integer} selectActivityTime   当前选择持续时间
     * @apiSuccess (time Success 200) {Integer} selectPrice   当前选择时常价格
     * @apiSuccess (time Success 200) {Integer} timeInterval   收费间隔
     *
     * @apiSuccess (personal Success 200) {String="normal","ticket","time","personal","game"} roomType 	房间类型
     * @apiSuccess (personal Success 200) {Timestamp} startTime 开始时间
     * @apiSuccess (personal Success 200) {Integer} activityTimeList   继续时间=[30,60,90,120]
     * @apiSuccess (personal Success 200) {Integer} userId		贵宾的用户id=1
     * @apiSuccess (personal Success 200) {Integer} selectActivityTime   当前选择持续时间
     *
     */
    @RequestMapping(value = "/getConfigInfo", method = RequestMethod.GET)
    @Override
    public ReadyPlayResponseVo getConfigInfo(Integer userId, String token) throws ServiceException {
        //（1）验证参数：是否合法
        ZbTZhubo zbTZhubo = this.zhuboDao.findByUserId(userId);
        if (zbTZhubo == null || zbTZhubo.getZbId() == null) {
            throw new ServiceException("主播不存在，请您先申请主播");
        }
        Integer zbId = zbTZhubo.getZbId();
        ReadyPlayResponseVo responseVo = new ReadyPlayResponseVo();
        ZbTQvodConfigs config=this.qconfigDao.findByConfig("download_url");
        if (config==null){
            responseVo.setDownload_url("无法获取download_url地址");
        }else{
            responseVo.setDownload_url(config.getDescription());
        }
        //（2）获取主播权限：
        Integer status = zbTZhubo.getZbStatus();
        if (ZbConstant.ZB.status.disable == status) {
            throw new ServiceException("直播已经被禁播，请联系房管");
        }
        Integer isTicket=zbTZhubo.getTicketState();
        Integer isTime=zbTZhubo.getTimeState();
        Integer isPersonal=zbTZhubo.getPersonalState();
        Integer isGame=zbTZhubo.getGameState();
        //  2B.判断是否拥有 时常房间、门票房间、私密房间、游戏房间的开播权限</br>
        if( isTicket==0 && isTime==0){
            throw new ServiceException("您没有时长房间与门票房间的权限，请联系房管");
        }
        //（3）获取房间属性：
        ZbTRoomPlan zbTRoomPlan = this.roomPlanDao.findByZbId(zbId);
        if (zbTRoomPlan!=null){
            responseVo.setRoomId(zbTRoomPlan.getRoomId());
            responseVo.setRoomStatus(zbTRoomPlan.getRoomStatus());
            responseVo.setRoomTitle(zbTRoomPlan.getRoomTitle());
            responseVo.setRoomBackgroundImg(zbTRoomPlan.getRoomBgPic());
            responseVo.setRoomType(zbTRoomPlan.getRoomType());
            responseVo.setOpenTime(new Timestamp(zbTRoomPlan.getOpenTime().getTime()));
//            responseVo.setResult("房间正在直播中，可以配置房间类型");
        }

        List<Object[]> list1=roomTypePriceDao.listByRoomPriceList(ZbConstant.Room.Type.ticket.name());
        String activityTimeList1=null;
        String priceList1=null;
        if (list1.size()==1){
            activityTimeList1=(String) list1.get(0)[1];
            priceList1=(String) list1.get(0)[2];
        }
        Map<String, Object> ticketConf = new HashMap<>();
        ticketConf.put("startTime", new Timestamp(System.currentTimeMillis()));//当前类型直播开始的时间
        ticketConf.put("activityTimeList",activityTimeList1);
        ticketConf.put("priceList", priceList1);
        if (isTicket==1){
            responseVo.setTicketConf(ticketConf);
        }else {
            responseVo.setTicketConf(new HashMap<>());
        }
        List<Object[]> list2=roomTypePriceDao.listByRoomPriceList(ZbConstant.Room.Type.time.name());
        String activityTimeList2=null;
        String priceList2=null;
        String timeInterval=null;
        if (list2.size()==1){
            activityTimeList2=(String) list2.get(0)[1];
            priceList2=(String) list2.get(0)[2];
            timeInterval=(String) list2.get(0)[3];
        }

        Map<String, Object> timeConf = new HashMap<>();
        timeConf.put("startTime", new Timestamp(System.currentTimeMillis()));//当前类型直播开始的时间
        timeConf.put("activityTimeList", activityTimeList2);
        timeConf.put("priceList", priceList2);
        timeConf.put("timeInterval", timeInterval);
        if (isTime==1){
            responseVo.setTimeConf(timeConf);
        }else {
            responseVo.setTimeConf(new HashMap<>());
        }

        List<Object[]> list3=roomTypePriceDao.listByRoomPriceList(ZbConstant.Room.Type.personal.name());
        String activityTimeList3=null;
        if (list3.size()==1){
            activityTimeList3=(String) list3.get(0)[1];
        }

        Map<String, Object> personalConf = new HashMap<>();
        personalConf.put("startTime", new Timestamp(System.currentTimeMillis()));//当前类型直播开始的时间
        personalConf.put("activityTimeList", activityTimeList3);
        if (isPersonal==1){
            responseVo.setPersonalConf(personalConf);
        }else {
            responseVo.setPersonalConf(new HashMap<>());
        }
        if (isGame==1){
            responseVo.setGameConf(new HashMap<>());
        }else {
            responseVo.setGameConf(new HashMap<>());
        }
        ZbTRoom zbTRoom = this.roomDao.findByZbId(zbId);
        if (zbTRoom == null) {
            zbTRoom = new ZbTRoom();
            zbTRoom.setZbId(zbId);
            zbTRoom.setRoomStatus(ZbConstant.Room.status.room_new);
            zbTRoom.setCreate_Time(new Timestamp(System.currentTimeMillis()));
            this.roomDao.save(zbTRoom);
            responseVo.setRoomStatus(zbTRoom.getRoomStatus());
        }
        responseVo.setRoomId(zbTRoom.getRoomId());
        responseVo.setRoomTitle(zbTRoom.getRoomTitle());
        responseVo.setRoomBackgroundImg(zbTRoom.getRoomBgPic());
        responseVo.setResult("获取房间配置信息成功");
        return responseVo;
    }

    /**
     * > 主播API - 更改房间类型信息</br>
     * > 更改房间类型信息流程</br>
     * > （1）验证参数：是否合法</br>
     * > （2）验证房间状态：</br>
     * >             A.未开播（=2）直接返回</br>
     * >             B.直播中（=1）直接进入直播间，更新DB+缓存直播标题 等数据</br>
     * > （3）维护表：zb_t_room与zb_t_room_plan</br>
     * >             A.查询当前房间场次，确保缓存中没场次（用户没有直播），直接返回</br>
     * >             B.如果有场次，更新存储到缓存 房间信息，主播信息（主播昵称、等级、主播头像等），房间属性配置信息，场次信息</br>
     * > （4）组件返回值：</br>
     * >             A.planId,status,result</br>
     *
     * @apiParam (ticket) {String="normal","ticket","time","personal","game"} roomType 	房间类型
     * @apiParam (ticket) {String{0..10}} roomTitle 房间标题
     * @apiParam (ticket)  {Long} startTime 开始时间（时间戳）
     * @apiParam (ticket)  {Integer} activityTime   继续时间=30,60,90,120  其中选择值
     * @apiParam (ticket)  {Integer} price   门票单价=20,50,100,120  其中选择值
     *
     * @apiParam (time) {String="normal","ticket","time","personal","game"} roomType 	房间类型
     * @apiParam (time) {String{0..10}} roomTitle 房间标题
     * @apiParam (time)  {Long} startTime 开始时间（时间戳）
     * @apiParam (time)  {Integer} activityTime   继续时间=30,60,90,120  其中选择值
     * @apiParam (time)  {Integer} price   每分钟单价=1,2,5,10  其中选择值
     *
     * @apiParam (personal) {String="normal","ticket","time","personal","game"} roomType 	房间类型
     * @apiParam (personal) {String{0..10}} roomTitle 房间标题
     * @apiParam (personal)  {Long} startTime 开始时间（时间戳）
     * @apiParam (personal)  {Integer} activityTime   继续时间=30,60,90,120  其中选择值
     * @apiParam (personal)  {Integer} userId		贵宾的用户id=1
     *
     * @apiSuccess {Integer} planId  场次Id
     * @apiSuccess {Integer} roomStatus 0=创建，1=直播中，2=未开播，3=回放中
     * @apiSuccess {String} result  开播成功或失败
     *
     */
    @RequestMapping(value = "/changeRoom", method = RequestMethod.POST)
    @Override
    public StartPlayResponseVo changeRoom(Integer userId, @RequestBody StartPlayRequestVo startPlayRequestVo) throws ServiceException {
        //（1）验证参数：是否合法
        ZbTZhubo zbTZhubo = this.zhuboDao.findByUserId(userId);
        if (zbTZhubo == null || zbTZhubo.getZbId() == null) {
            throw new ServiceException("主播不存在，请您先申请主播");
        }
        Integer zbId = zbTZhubo.getZbId();
        ZbTRoom zbTRoom = this.roomDao.findByZbId(zbId);
        if (zbTRoom == null) {
            throw new ServiceException("主播的房间不存在");
        }

        String roomType = startPlayRequestVo.getRoomType();
        if (StringUtils.isEmpty(roomType)) {
            throw new ServiceException("房间类型不能为空");
        }
        try {
            ZbConstant.Room.Type.valueOf(roomType);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("房间类型错误");
        }
//        if (ZbConstant.Room.Type.game.name().equals(roomType)) {
//            throw new ServiceException("房间尽请期待");
//        }

        String roomTitle = startPlayRequestVo.getRoomTitle();
        if (StringUtils.isEmpty(roomTitle)) {
            throw new ServiceException("房间标题不能为空");
        }
        if (roomTitle.length() > 10) {
            throw new ServiceException("房间标题最多10个汉字");
        }
        if (ZbConstant.Room.Type.ticket.name().equals(roomType) || ZbConstant.Room.Type.time.name().equals(roomType)) {
            if (startPlayRequestVo.getStartTime() == null) {
                throw new ServiceException("开始时间不能为空");
            }
            Integer activityTime = startPlayRequestVo.getActivityTime();
            if (activityTime == null) {
                throw new ServiceException("持续时间不能为空");
            }
            Integer price = startPlayRequestVo.getPrice();
            if (price == null) {
                throw new ServiceException("单价不能为空");
            }
        }
        ZbTUserPersonal zbTUserPersonal = null;
        if (ZbConstant.Room.Type.personal.name().equals(roomType)) {
            if (startPlayRequestVo.getStartTime() == null) {
                throw new ServiceException("开始时间不能为空或者格式错误，请传Long类型的时间戳");
            }
            Integer activityTime = startPlayRequestVo.getActivityTime();
            if (activityTime == null) {
                throw new ServiceException("持续时间不能为空");
            }
            Integer userClientId = startPlayRequestVo.getUserId();
            if (userClientId == null) {
                throw new ServiceException("贵宾用户不能为空");
            }
            zbTUserPersonal = this.userPersonalDao.findByUserIdAndZbId(userClientId, zbId);
            if (zbTUserPersonal == null) {
                throw new ServiceException("贵宾用户可能没有提前预约主播");
            }
        }
        //（2）验证房间状态：</br>
        //  A.未开播（=2）直接返回</br>
        //   B.直播中（=1）直接进入直播间，更新DB+缓存直播标题 等数据</br>
        ZbTRoomPlan zbTRoomPlan = this.roomPlanDao.findByRoomId(zbTRoom.getRoomId());
        if (zbTRoomPlan == null){
            throw new ServiceException("当前房间未开播，不能切换房间类型");
        }
        if (ZbConstant.Room.status.room_redio == zbTRoom.getRoomStatus()) {
            StartPlayResponseVo startPlayResponseVo = new StartPlayResponseVo();
            startPlayResponseVo.setPlanId(zbTRoomPlan.getPlanId());
            startPlayResponseVo.setRoomStatus(zbTRoom.getRoomStatus());
            startPlayResponseVo.setResult("房间正在回放中，不能切换房间类型");
            return startPlayResponseVo;
        }

        //(3.) 更新房间信息
        this.roomDao.updateRoomStatusAndAndRoomTitle(zbTRoom.getRoomId(), ZbConstant.Room.status.room_ing, startPlayRequestVo.getRoomTitle());
        zbTRoomPlan.setRoomTitle(roomTitle);
        //(3.B)维护表：zb_t_room与zb_t_room_plan B.更新缓存 房间类型

        zbTRoomPlan.setRoomType(startPlayRequestVo.getRoomType());
        zbTRoomPlan.setRoomStatus(ZbConstant.Room.status.room_ing);     //同步记录房间状态
        zbTRoomPlan.setRoomBgPic(zbTRoom.getRoomBgPic());

        ZbUcUser ucUser=this.ucUserDao.findOne(userId);
        zbTRoomPlan.setZbNickname(ucUser.getNickname());
        zbTRoomPlan.setZbLevel(ucUser.getLevel()*1);
        zbTRoomPlan.setZbHeadImg(ucUser.getAvatar());
        zbTRoomPlan.setZbSignature(ucUser.getSignature());
        //(3)创建房间配置信息，先获取配置信息，一并存入
        if (ZbConstant.Room.Type.ticket.name().equals(roomType)) {
            List<Object[]> list1=roomTypePriceDao.listByRoomPriceList(ZbConstant.Room.Type.ticket.name());
            ZbTQvodZbTRoomTypePrice roomTypePrice=roomTypePriceDao.findByTypeAndAndTime(ZbConstant.Room.Type.ticket.name(),startPlayRequestVo.getActivityTime());
            if (roomTypePrice==null){
                throw new ServiceException("门票时间参数有误");
            }

            Set<Integer> ticketPR = new TreeSet<>();
            String[] temp=roomTypePrice.getPrice().split(",");
            Integer[] result = new Integer[temp.length];//int类型数组
            for(int i=0;i<temp.length;i++) {
                result[i] = Integer.parseInt(temp[i]);//整数数组
            }
            ticketPR.addAll(Arrays.asList(result));
            if (!ticketPR.contains(startPlayRequestVo.getPrice())) {
                throw new ServiceException("门票价格参数有误");
            }

            Map<String, Object> map = new HashMap<>();
            map.put("startTime", new Date(startPlayRequestVo.getStartTime()));//当前类型直播开始的时间
            map.put("activityTimeList", list1.get(0)[1]);
            map.put("selectActivityTime", startPlayRequestVo.getActivityTime());
            map.put("overTime", DateUtils.addMinutes(new Date(startPlayRequestVo.getStartTime()), startPlayRequestVo.getActivityTime()));
            map.put("priceList", StringUtils.join(ticketPR, ","));
            map.put("selectPrice", startPlayRequestVo.getPrice());
            zbTRoomPlan.setRoomSet(map);  //门票收费或者时长收费
        }

        if (ZbConstant.Room.Type.time.name().equals(roomType)) {
            List<Object[]> list2=roomTypePriceDao.listByRoomPriceList(ZbConstant.Room.Type.time.name());
            ZbTQvodZbTRoomTypePrice roomTypePrice=roomTypePriceDao.findByTypeAndAndTime(ZbConstant.Room.Type.time.name(),startPlayRequestVo.getActivityTime());
            if (roomTypePrice==null){
                throw new ServiceException("时长时间参数有误");
            }

            Set<Integer> timePR = new TreeSet<>();
            String[] temp=roomTypePrice.getPrice().split(",");
            Integer[] result = new Integer[temp.length];//int类型数组
            for(int i=0;i<temp.length;i++) {
                result[i] = Integer.parseInt(temp[i]);//整数数组
            }
            timePR.addAll(Arrays.asList(result));
            if (!timePR.contains(startPlayRequestVo.getPrice())) {
                throw new ServiceException("时长价格参数有误");
            }

            Map<String, Object> map = new HashMap<>();
            map.put("startTime", new Date(startPlayRequestVo.getStartTime()));//当前类型直播开始的时间
            map.put("activityTimeList", list2.get(0)[1]);
            map.put("selectActivityTime", startPlayRequestVo.getActivityTime());
            map.put("overTime", DateUtils.addMinutes(new Date(startPlayRequestVo.getStartTime()), startPlayRequestVo.getActivityTime()));
            map.put("priceList", StringUtils.join(timePR, ","));
            map.put("selectPrice", startPlayRequestVo.getPrice());
            map.put("timeInterval", roomTypePrice.getTimeInterval());
            zbTRoomPlan.setRoomSet(map);  //门票收费或者时长收费
        }

        if (ZbConstant.Room.Type.personal.name().equals(roomType)) {
            List<Object[]> list3=roomTypePriceDao.listByRoomPriceList(ZbConstant.Room.Type.personal.name());
            ZbTQvodZbTRoomTypePrice roomTypePrice=roomTypePriceDao.findByTypeAndAndTime(ZbConstant.Room.Type.personal.name(),startPlayRequestVo.getActivityTime());
            if (roomTypePrice==null){
                throw new ServiceException("私人房间时间参数有误");
            }

            Set<Integer> personalPR = new TreeSet<>();
            String[] temp=roomTypePrice.getPrice().split(",");
            Integer[] result = new Integer[temp.length];//int类型数组
            for(int i=0;i<temp.length;i++) {
                result[i] = Integer.parseInt(temp[i]);//整数数组
            }
            personalPR.addAll(Arrays.asList(result));
            if (!personalPR.contains(startPlayRequestVo.getPrice())) {
                throw new ServiceException("私人房间价格参数有误");
            }

            Map<String, Object> map = new HashMap<>();
            map.put("startTime", new Date(startPlayRequestVo.getStartTime()));//当前类型直播开始的时间
            map.put("selectActivityTime", startPlayRequestVo.getActivityTime());
            map.put("activityTimeList", list3.get(0)[1]);
            map.put("overTime", DateUtils.addMinutes(new Date(startPlayRequestVo.getStartTime()), startPlayRequestVo.getActivityTime()));
            map.put("userId", startPlayRequestVo.getUserId());
            zbTRoomPlan.setRoomSet(map);  //私密收费
            //(3.D).如果是私密房间，更新用户预约表zb_t_user_personal的邀请时间，已经房间预约信息
            zbTUserPersonal.setActivityTime(startPlayRequestVo.getActivityTime());
            zbTUserPersonal.setStartTime(new Date(startPlayRequestVo.getStartTime()));
            zbTUserPersonal.setInviteTime(new Date());
            this.userPersonalDao.save(zbTUserPersonal);
        }
        this.roomPlanDao.save(zbTRoomPlan);

        //TODO （4）关注用户的消息推送，自己已关注的主播进行消息提醒，勾选了的主播才能进行消息推送

        //(5) 返回结果
        StartPlayResponseVo startPlayResponseVo = new StartPlayResponseVo();
        startPlayResponseVo.setRoomId(zbTRoom.getRoomId());
        startPlayResponseVo.setPlanId(zbTRoomPlan.getPlanId());
        startPlayResponseVo.setRoomStatus(ZbConstant.Room.status.room_ing);
        startPlayResponseVo.setResult("主播房间类型切换成功");
        return startPlayResponseVo;
    }
}
