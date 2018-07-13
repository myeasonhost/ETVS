package com.eason.api.zb.service.impl;

import com.eason.api.zb.IIndexService;
import com.eason.api.exception.ServiceException;
import com.eason.api.zb.dao.db.IndexBannerDao;
import com.eason.api.zb.dao.db.MsgNotificationDao;
import com.eason.api.zb.dao.mongo.RoomPlanDao;
import com.eason.api.zb.dao.mongo.po.ZbTRoomPlan;
import com.eason.api.zb.model.PageModel;
import com.eason.api.zb.model.ZbConstant;
import com.eason.api.zb.dao.db.po.*;
import com.eason.api.zb.vo.index.BannerResponseVo;
import com.eason.api.zb.vo.index.IndexResponseVo;
import com.eason.api.zb.vo.index.MsgNotificationResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index")
public class IndexServiceImpl implements IIndexService {
    private static Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);

    @Autowired
    private IndexBannerDao indexBannerDao;
    @Autowired
    private MsgNotificationDao msgNotificationDao;
    @Autowired
    private RoomPlanDao roomPlanDao;

    @RequestMapping(value = "/{category}/getIndexList/{position}/{pageSize}",method = RequestMethod.GET)
    @Override
    public PageModel<IndexResponseVo> getIndexList(@PathVariable(value = "category") String category,@PathVariable(value = "position") Integer position,@PathVariable(value = "pageSize")  Integer pageSize)  throws ServiceException{
        try {
            PageModel pageModel=new PageModel();
            List<IndexResponseVo> list = new ArrayList<>();
            Page<ZbTRoomPlan> page=null;
            if("1".equals(category)){ //最热=1
                Sort sort = new Sort(Sort.Direction.DESC,"orderField").and(new Sort(Sort.Direction.DESC,"incomeAmountToday"));
                Pageable pageable = new PageRequest(position, pageSize, sort);
                page=roomPlanDao.findAll(pageable);
            }else if ("2".equals(category)){ //收藏=2
                Sort sort = new Sort(Sort.Direction.DESC,"orderField").and(new Sort(Sort.Direction.DESC,"openTime"));
                Pageable pageable = new PageRequest(position, pageSize, sort);
                page=  roomPlanDao.findAll(pageable);
            }else{
               throw new ServiceException("首页列表只支持category=1，2类型");
            }
            pageModel.setTotal(page.getTotalPages());
            page.getContent().forEach(zbTRoomPlan ->{
                IndexResponseVo indexResponseVo=new IndexResponseVo();
                indexResponseVo.setRoomId(zbTRoomPlan.getRoomId());
                indexResponseVo.setRoomPlanId(zbTRoomPlan.getPlanId());
                indexResponseVo.setUserId(zbTRoomPlan.getUserId());
                indexResponseVo.setUsername(zbTRoomPlan.getUsername());
                indexResponseVo.setNickname(zbTRoomPlan.getNickname());
                indexResponseVo.setAvatar(zbTRoomPlan.getAvatar());
                indexResponseVo.setRoomTitle(zbTRoomPlan.getRoomTitle());
                indexResponseVo.setOnlineUser(zbTRoomPlan.getOnlineUser()!=null?zbTRoomPlan.getOnlineUser():0);
                indexResponseVo.setMachineUser(zbTRoomPlan.getMachineUser()!=null?zbTRoomPlan.getMachineUser():0);
                indexResponseVo.setViewCount(zbTRoomPlan.getViewCount()!=null?zbTRoomPlan.getViewCount():0);
                indexResponseVo.setWatchCount(zbTRoomPlan.getWatchCount()!=null?zbTRoomPlan.getWatchCount():0);
                indexResponseVo.setRoomBackgroundImg(zbTRoomPlan.getRoomBgPic());
                indexResponseVo.setRoomStatus(zbTRoomPlan.getRoomStatus());
                indexResponseVo.setStartTime(new Timestamp(zbTRoomPlan.getOpenTime().getTime()));

                list.add(indexResponseVo);
            });
             pageModel.setTotal(1);
            pageModel.setRows(list);
            return pageModel;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @RequestMapping(value = "/{category}/getBannerList",method = RequestMethod.GET)
    @Override
    public List<BannerResponseVo> getBannerList( @PathVariable(value = "category") String category)  throws ServiceException{
        try {
            List<BannerResponseVo> list = new ArrayList<BannerResponseVo>();
            if (!("1".equals(category) || "2".equals(category))){
                throw new ServiceException("首页列表只支持category=1，2类型");
            }

            List<ZbTQvodBanners> bannerList=indexBannerDao.getByType(Integer.parseInt(category)+1);
            bannerList.forEach(indexBanner->{
                list.add(new BannerResponseVo(indexBanner.getId(), indexBanner.getTitle(), indexBanner.getThumbImgUrl(), indexBanner.getType()+"", indexBanner.getUrl(),indexBanner.getUrlType(),indexBanner.getStatus()));
            });
            return list;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @RequestMapping(value = "/{category}/getMsgNotificationList",method = RequestMethod.GET)
    @Override
    public List<MsgNotificationResponseVo> getMsgNotification( @PathVariable(value = "category") String category) throws ServiceException{
        try {
            if (!("1".equals(category) || "2".equals(category))){
                throw new ServiceException("首页列表只支持category=1，2");
            }
            List<MsgNotificationResponseVo> list = new ArrayList<MsgNotificationResponseVo>();
            List<ZbTQvodNews> msgNotificationList=msgNotificationDao.getAll();
            msgNotificationList.forEach(zbTQvodNews -> {
                list.add(new MsgNotificationResponseVo(zbTQvodNews.getId(),zbTQvodNews.getTitle(),zbTQvodNews.getUrl()));
            });
            return list;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
