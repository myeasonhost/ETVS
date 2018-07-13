define({ "api": [
  {
    "version": "1.0.0",
    "group": "gift",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "GET",
    "url": "/gift/getList",
    "title": "礼物列表",
    "name": "getList",
    "description": "<blockquote> <p>点击送礼按钮，进入礼物列表</br></p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "giftId",
            "description": "<p>礼物ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "giftName",
            "description": "<p>礼物名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "giftImg",
            "description": "<p>礼物图片 地址</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "giftPrice",
            "description": "<p>礼物价格</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "specialStyle",
            "description": "<p>特效方式</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IGiftService.java",
    "groupTitle": "礼物API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/gift/getList"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "gift",
    "permission": [
      {
        "name": "IM"
      }
    ],
    "type": "POST",
    "url": "/gift/sendBombScreen/{zbId}",
    "title": "发送飞屏",
    "name": "sendBombScreen",
    "description": "<blockquote> <p>在聊天窗口，选择飞屏，发送飞屏消息</br> 待定：IM实现？还是API实现？</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "msgText",
            "description": "<p>飞屏内容</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户ID</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "diamondBalance",
            "description": "<p>用户钻石余额</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "cost",
            "description": "<p>消费金额</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IGiftService.java",
    "groupTitle": "礼物API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/gift/sendBombScreen/{zbId}"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "gift",
    "permission": [
      {
        "name": "IM"
      }
    ],
    "type": "POST",
    "url": "/gift/sendGift/{zbId}",
    "title": "发送礼物",
    "name": "sendGift",
    "description": "<blockquote> <p>在礼物列表，选好礼物跟价格，发送礼物</br> 待定：IM实现？还是API实现？</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "giftId",
            "description": "<p>礼物id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "giftNum",
            "description": "<p>礼物数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户ID</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "diamondBalance",
            "description": "<p>用户钻石余额</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "cost",
            "description": "<p>消费金额</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IGiftService.java",
    "groupTitle": "礼物API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/gift/sendGift/{zbId}"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "index",
    "type": "GET",
    "url": "/index/{category}/getBannerList",
    "title": "2.直播首页Banner列表",
    "name": "getBannerList",
    "description": "<blockquote> <p>需要显示Banner的模块：最新、最热</br> category 房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏</br> 没有这么多需求，默认就传category=1</p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "id",
            "description": "<p>BannerID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>Banner标题</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "thumb_img_url",
            "description": "<p>Banner图片地址</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "url",
            "description": "<p>跳转地址</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "url_type",
            "description": "<p>Banner跳转类型（1:链接 2:应用内界面 3:指定视频）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>状态（1=正常、0=删除）</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IIndexService.java",
    "groupTitle": "直播首页API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/index/{category}/getBannerList"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "index",
    "type": "GET",
    "url": "/index/{category}/getIndexList/{position}/{pageSize}",
    "title": "1.房间列表",
    "name": "getIndexList",
    "description": "<blockquote> <p>房间分类：1=最热（默认）、2=最新 </br> 没有这么多需求，默认就传category=1</p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "total",
            "description": "<p>总数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "rows",
            "description": "<p>rows-&gt;row</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.roomId",
            "description": "<p>房间ID</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.userId",
            "description": "<p>主播ID（用户ID）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.roomPlanId",
            "description": "<p>房间场次ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.username",
            "description": "<p>主播账号（用户名）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.nickname",
            "description": "<p>主播昵称（用户昵称）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.avatar",
            "description": "<p>主播头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.roomTitle",
            "description": "<p>房间标题</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.onlineUser",
            "description": "<p>真实在线用户</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.machineUser",
            "description": "<p>机器用户</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.viewCount",
            "description": "<p>总浏览次数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.watchCount",
            "description": "<p>总观看次数</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.roomBackgroundImg",
            "description": "<p>房间背景图片</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.roomStatus",
            "description": "<p>直播状态： 1=直播中（2=回放中）</p>"
          },
          {
            "group": "Success 200",
            "type": "Timestamp",
            "optional": false,
            "field": "row.startTime",
            "description": "<p>房间开播时间</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IIndexService.java",
    "groupTitle": "直播首页API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/index/{category}/getIndexList/{position}/{pageSize}"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "index",
    "type": "GET",
    "url": "/index/{category}/getMsgNotificationList",
    "title": "3.公告消息列表",
    "name": "getMsgNotificationList",
    "description": "<blockquote> <p>需要显示MsgNotification的模块：最新、最热</br> category 房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏</br> 没有这么多需求，默认就传category=1</p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "id",
            "description": "<p>公告消息ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>公告消息内容</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "url",
            "description": "<p>点击事件</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IIndexService.java",
    "groupTitle": "直播首页API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/index/{category}/getMsgNotificationList"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "live",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "GET",
    "url": "/live/getPlayUrl/{roomId}",
    "title": "2.获取拉流地址",
    "name": "getPlayUrl",
    "description": "<blockquote> <p>从缓存获取拉流地址</br></p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "playUrl",
            "description": "<p>获取拉流地址</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/ILiveUrlService.java",
    "groupTitle": "流媒体API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/live/getPlayUrl/{roomId}"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "live",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "GET",
    "url": "/live/getPlayUrlList",
    "title": "3.获取拉流地址列表",
    "name": "getPlayUrlList",
    "description": "<blockquote> <p>获取拉流地址列表</br></p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "getPlayUrlList",
            "description": "<p>getPlayUrlList-&gt;ZbRoomLiveVo（流列表）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "ZbRoomLiveVo.liveVo.id",
            "description": "<p>流ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "ZbRoomLiveVo.liveVo.roomId",
            "description": "<p>房间ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "ZbRoomLiveVo.liveVo.playUrl",
            "description": "<p>拉流ID</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/ILiveUrlService.java",
    "groupTitle": "流媒体API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/live/getPlayUrlList"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "live",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "GET",
    "url": "/live/getPushUrl/{roomId}",
    "title": "1.获取推流地址",
    "name": "getPushUrl",
    "description": "<blockquote> <p>首次获取流地址，会自动生成推流地址和拉流地址，并存入缓存</br> 再次获取流地址，会自动更新推流地址和拉流地址，并更新缓存</br></p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "pushUrl",
            "description": "<p>获取推流地址</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/ILiveUrlService.java",
    "groupTitle": "流媒体API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/live/getPushUrl/{roomId}"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "live",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "GET",
    "url": "/live/removeLiveInfo/{roomId}",
    "title": "4.删除缓存流信息",
    "name": "removeLiveInfo",
    "description": "<blockquote> <p>删除缓存流信息，在主播直播完成之后，触犯该动作</br></p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "info",
            "description": "<p>删除成功或者失败</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/ILiveUrlService.java",
    "groupTitle": "流媒体API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/live/removeLiveInfo/{roomId}"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "room",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "GET",
    "url": "/room/createRoom?token=xxxxxx",
    "title": "2.创建房间",
    "name": "createRoom",
    "description": "<blockquote> <p>如果没有房间，创建房间，如果有房间，返回原来的房间设置的属性</br></p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "roomId",
            "description": "<p>房间ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "roomTitle",
            "description": "<p>房间标题</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "roomBgPic",
            "description": "<p>房间封面</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "roomStatus",
            "description": "<p>房间状态</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "result",
            "description": "<p>提示信息</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IRoomService.java",
    "groupTitle": "房间API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/room/createRoom?token=xxxxxx"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "room",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "GET",
    "url": "/room/{roomId}/enterRoom",
    "title": "3.进入房间",
    "name": "enterRoom",
    "description": "<blockquote> <p>用户与主播，进入房间，获取房间详情明细，包含如下几个动作：</br> （1）获取房间详情</br> （2）获取主播用户详情</br> （3）获取粉丝用户列表</br> （4）获取IM/Video服务器地址</br></p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "planId",
            "description": "<p>房间场次ID</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "roomId",
            "description": "<p>房间ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "roomTitle",
            "description": "<p>房间标题</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "onlineUser",
            "description": "<p>房间当前在线用户</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "machineUser",
            "description": "<p>房间机器人用户</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "roomBackgroundImg",
            "description": "<p>房间背景图片</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>主播的用户ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "zbNickname",
            "description": "<p>主播昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "zbLevel",
            "description": "<p>主播等级</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "zbHeadImg",
            "description": "<p>主播头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "zbSignature",
            "description": "<p>主播个性签名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "playUrl",
            "description": "<p>拉流地址</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "imUrl",
            "description": "<p>聊天室地址</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IRoomService.java",
    "groupTitle": "房间API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/room/{roomId}/enterRoom"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "room",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/room/setRoomBackgroundImg?token=xxxxxx",
    "title": "1.设置房间直播封面",
    "name": "setRoomBackgroundImg",
    "description": "<blockquote> <p>进入主播开播界面，设置直播房间封面</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "byte[]",
            "optional": false,
            "field": "roomBackgroundImg",
            "description": "<p>房间背景图</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "imgUrl",
            "description": "<p>上传地址</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IRoomService.java",
    "groupTitle": "房间API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/room/setRoomBackgroundImg?token=xxxxxx"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "task",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "GET",
    "url": "/task/resetTrySee",
    "title": "重置试看时间",
    "name": "resetTrySee",
    "description": "<blockquote> <p>每天凌晨4点，重置试看</br></p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "result",
            "description": "<p>重置成功或者失败</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/ITaskService.java",
    "groupTitle": "任务API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/task/resetTrySee"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "zhubo",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/zhubo/startPlay?token=xxxxxx",
    "title": "1.开始直播",
    "name": "startPlay",
    "description": "<blockquote> <p>主播API - 开始直播</br> 点击开始直播：</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "String": [
          {
            "group": "String",
            "type": "String",
            "size": "0..10",
            "optional": false,
            "field": "roomTitle",
            "description": "<p>房间标题</p>"
          },
          {
            "group": "String",
            "optional": false,
            "field": "roomBackgroundImg",
            "description": "<p>房间背景图片</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "planId",
            "description": "<p>场次Id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "roomId",
            "description": "<p>房间Id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "result",
            "description": "<p>开播提示信息</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IZhuboService.java",
    "groupTitle": "主播API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/zhubo/startPlay?token=xxxxxx"
      }
    ]
  }
] });
