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
    "description": "<blockquote> <p>房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏 </br> 【最热=1】：砖石数排序（大-小）,主播昵称、直播标题、房间类型（若普通房间收费模式不现实）、房间人数（真实人数和机器人数）、网络状态（API不提供）</br> 【最新=3】：主播开播时间（近-远）排序，主播昵称、房间类型、房间人数（真实人数和机器人数）、网络状态（API不提供）</br> 【收藏=2】：显示关注后的主播房间,顺序直播类型-录播类型</br> (1)直播类型=砖石数排序（大-小）,直播状态、房间人数、主播昵称、开播信息、网络状态、收费模式（普通房间不显示）</br> (2)录播类型=发布日期（近-远），被观看次数、收费价格（普通房间不显示）、主播昵称、开播信息</br> 【付费=4】：砖石数排序（大-小）,开播标题、房间类型、房间人数（真实人数和机器人数）、网络状态（API不提供）</br> 【游戏=5】：房间游戏投注数排序（大-小）,主播昵称、开播标题、游戏类型、参与人数（如上第二条解释）、网络状态（API不提供）</br> 没有这么多需求，默认就传category=1</p> </blockquote>",
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
            "field": "row.zbId",
            "description": "<p>主播ID</p>"
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
            "field": "row.zbNickName",
            "description": "<p>主播昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.zbHeadImg",
            "description": "<p>主播头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.zbLevel",
            "description": "<p>主播等级</p>"
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
            "type": "String",
            "optional": false,
            "field": "row.roomType",
            "description": "<p>房间类型：'normal=普通房间','ticket=门票房间','time=时常房间','personal=私密房间','game=游戏房间'</p>"
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
            "description": "<p>直播状态： 1=直播中，2=未开播，3=回放中</p>"
          },
          {
            "group": "Success 200",
            "type": "Timestamp",
            "optional": false,
            "field": "row.startTime",
            "description": "<p>房间开播时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "row.gameIcon",
            "description": "<p>游戏图标</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.playUrl",
            "description": "<p>录像回放地址</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.isCharge",
            "description": "<p>是否收费   0=不收费，1=收费</p>"
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
    "title": "获取拉流地址",
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
    "title": "获取拉流地址列表",
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
    "title": "获取推流地址",
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
    "title": "删除缓存流信息",
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
    "description": "<blockquote> <p>创建房间，如果有，返回原来的房间设置的属性</br></p> </blockquote>",
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
    "title": "进入房间",
    "name": "enterRoom",
    "description": "<blockquote> <p>用户与主播，进入房间，获取房间详情明细，包含如下几个动作：</br> （1）获取房间详情</br> （2）获取主播详情</br> （3）获取用户详情</br> （4）获取粉丝用户列表</br> （5）获取IM/Video服务器地址</br></p> </blockquote>",
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
            "type": "String",
            "optional": false,
            "field": "roomType",
            "description": "<p>房间类型：'normal=普通房间','ticket=门票房间','time=时常房间','personal=私密房间','game=游戏房间'</p>"
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
            "type": "Integer",
            "optional": false,
            "field": "diamondGiftNum",
            "description": "<p>房间钻石礼物总数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "roomNo1",
            "description": "<p>房间排名</p>"
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
            "field": "zbId",
            "description": "<p>主播ID</p>"
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
            "type": "Integer",
            "optional": false,
            "field": "isAttention",
            "description": "<p>用户是否关注</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userLevel",
            "description": "<p>用户等级息</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "download_url",
            "description": "<p>下载地址</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "diamondBalance",
            "description": "<p>用户钻石余额（星钻）</p>"
          },
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "diamondRankList",
            "description": "<p>diamondRankList-&gt;user（送礼钻石排行）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "diamondRankList.user.userId",
            "description": "<p>用户ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "diamondRankList.user.nickName",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "diamondRankList.user.sex",
            "description": "<p>用户性别</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "diamondRankList.user.userHeadImg",
            "description": "<p>用户头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "diamondRankList.user.userLevel",
            "description": "<p>用户等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "diamondRankList.user.giftRankNo1",
            "description": "<p>当前送礼排行</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "diamondRankList.user.diamondGiftUserTotal",
            "description": "<p>当前用户在当前房间累计送礼总数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "userLevelRankList",
            "description": "<p>userLevelRankList-&gt;user（用户等级排行）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userLevelRankList.user.userId",
            "description": "<p>用户ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userLevelRankList.user.nickName",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userLevelRankList.user.sex",
            "description": "<p>用户性别</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userLevelRankList.user.userHeadImg",
            "description": "<p>用户头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userLevelRankList.user.userLevel",
            "description": "<p>用户等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "media",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "media.type",
            "description": "<p>视频流类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "media.url",
            "description": "<p>视频流地址</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "im",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "im.type",
            "description": "<p>即时通讯类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "im.url",
            "description": "<p>即时通讯地址</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "im.port",
            "description": "<p>即时通讯端口</p>"
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
    "type": "GET",
    "url": "/room/{roomId}/isCharged",
    "title": "房间是否收费",
    "name": "isCharged",
    "description": "<blockquote> <p>判断房间是否收费，获取收费条件等信息</br> （1）房间收费类型roomType=ticket，time，personal</br> （2）2018-5-15日，新需求接口升级：房间收费接口—&gt;改名为点击房间权限判断接口（权限优先级为：是否禁止入内、是否收费）</br> （A）流程上发生变化：先判断是否禁止入内（新需求），在判断是否收费（原有逻辑不变）</br> （B）在原结构上，多加了一个bannedInfo字段，如果bannedInfo不为空，表示禁止入内，禁止的原因在bannedInfo结构体里面，bannedInfo==null，表示允许入内，在判断是否收费</br></p> </blockquote>",
    "success": {
      "fields": {
        "ticket Success 200": [
          {
            "group": "ticket Success 200",
            "type": "Integer",
            "optional": false,
            "field": "roomId",
            "description": "<p>房间ID</p>"
          },
          {
            "group": "ticket Success 200",
            "type": "Integer",
            "optional": false,
            "field": "zbId",
            "description": "<p>主播ID</p>"
          },
          {
            "group": "ticket Success 200",
            "type": "String",
            "allowedValues": [
              "\"normal\"",
              "\"ticket\"",
              "\"time\"",
              "\"personal\"",
              "\"game\""
            ],
            "optional": false,
            "field": "roomType",
            "description": "<p>房间类型</p>"
          },
          {
            "group": "ticket Success 200",
            "type": "Integer",
            "optional": false,
            "field": "ticketStatus",
            "description": "<p>0=未购买，1=购买</p>"
          },
          {
            "group": "ticket Success 200",
            "type": "Integer",
            "optional": false,
            "field": "selectPrice",
            "description": "<p>门票单价</p>"
          },
          {
            "group": "ticket Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>试看用户ID</p>"
          },
          {
            "group": "ticket Success 200",
            "type": "Integer",
            "optional": false,
            "field": "isTrySee",
            "description": "<p>0=未试看，1=已试看</p>"
          },
          {
            "group": "ticket Success 200",
            "type": "String",
            "optional": false,
            "field": "allowTime",
            "description": "<p>允许试看时间</p>"
          },
          {
            "group": "ticket Success 200",
            "type": "Long",
            "optional": false,
            "field": "usedTime",
            "description": "<p>已播时长</p>"
          },
          {
            "group": "ticket Success 200",
            "type": "Long",
            "optional": false,
            "field": "remainTime",
            "description": "<p>剩余时长</p>"
          }
        ],
        "time Success 200": [
          {
            "group": "time Success 200",
            "type": "Integer",
            "optional": false,
            "field": "roomId",
            "description": "<p>房间ID</p>"
          },
          {
            "group": "time Success 200",
            "type": "Integer",
            "optional": false,
            "field": "zbId",
            "description": "<p>主播ID</p>"
          },
          {
            "group": "time Success 200",
            "type": "String",
            "allowedValues": [
              "\"normal\"",
              "\"ticket\"",
              "\"time\"",
              "\"personal\"",
              "\"game\""
            ],
            "optional": false,
            "field": "roomType",
            "description": "<p>房间类型</p>"
          },
          {
            "group": "time Success 200",
            "type": "Integer",
            "optional": false,
            "field": "selectPrice",
            "description": "<p>时长单价</p>"
          },
          {
            "group": "time Success 200",
            "type": "Integer",
            "optional": false,
            "field": "timeInterval",
            "description": "<p>时常间隔（收费间隔）</p>"
          },
          {
            "group": "time Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>试看用户ID</p>"
          },
          {
            "group": "time Success 200",
            "type": "Integer",
            "optional": false,
            "field": "isTrySee",
            "description": "<p>0=未试看，1=已试看</p>"
          },
          {
            "group": "time Success 200",
            "type": "String",
            "optional": false,
            "field": "allowTime",
            "description": "<p>允许试看时间</p>"
          },
          {
            "group": "time Success 200",
            "type": "Long",
            "optional": false,
            "field": "usedTime",
            "description": "<p>已播时长</p>"
          },
          {
            "group": "time Success 200",
            "type": "Long",
            "optional": false,
            "field": "remainTime",
            "description": "<p>剩余时长</p>"
          }
        ],
        "personal Success 200": [
          {
            "group": "personal Success 200",
            "type": "Integer",
            "optional": false,
            "field": "roomId",
            "description": "<p>房间ID</p>"
          },
          {
            "group": "personal Success 200",
            "type": "Integer",
            "optional": false,
            "field": "zbId",
            "description": "<p>主播ID</p>"
          },
          {
            "group": "personal Success 200",
            "type": "String",
            "allowedValues": [
              "\"normal\"",
              "\"ticket\"",
              "\"time\"",
              "\"personal\"",
              "\"game\""
            ],
            "optional": false,
            "field": "roomType",
            "description": "<p>房间类型</p>"
          },
          {
            "group": "personal Success 200",
            "type": "Integer",
            "optional": false,
            "field": "personalStatus",
            "description": "<p>0=未预约，1=已预约</p>"
          },
          {
            "group": "personal Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>试看用户ID</p>"
          }
        ],
        "bannedInfo Success 200": [
          {
            "group": "bannedInfo Success 200",
            "type": "Integer",
            "optional": false,
            "field": "setSeconds",
            "description": "<p>设置的有效时间 ；-1永久被管理员禁言 其他是固定时间，0 未被禁足 单位秒</p>"
          },
          {
            "group": "bannedInfo Success 200",
            "type": "Integer",
            "optional": false,
            "field": "overSeconds",
            "description": "<p>剩余的有效时间 单位秒</p>"
          },
          {
            "group": "bannedInfo Success 200",
            "type": "Integer",
            "optional": false,
            "field": "nickName",
            "description": "<p>主播或者管理员</p>"
          },
          {
            "group": "bannedInfo Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>提示信息</p>"
          },
          {
            "group": "bannedInfo Success 200",
            "type": "Integer",
            "optional": false,
            "field": "role",
            "description": "<p>角色类型 1 主播 2 管理员</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IRoomService.java",
    "groupTitle": "房间API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/room/{roomId}/isCharged"
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
    "type": "GET",
    "url": "/zhubo/{zbId}/getGiftUserList/{category}",
    "title": "主播收礼排行用户列表",
    "name": "getGiftUserList",
    "description": "<blockquote> <p>点击钻石礼物，弹出礼物排行对话框</br> category =today，history</br> （1）点击当日，获取当日送礼排行</br> （2）点击全部，获取历史送礼排行</br></p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "userList",
            "description": "<p>userList-&gt;user</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userList.user.userId",
            "description": "<p>用户ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userList.user.nickName",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userList.user.sex",
            "description": "<p>用户性别</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userList.user.userHeadImg",
            "description": "<p>用户头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userList.user.userLevel",
            "description": "<p>用户等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userList.user.giftRankNo1",
            "description": "<p>当前送礼排行</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userList.user.diamondGiftUserTotal",
            "description": "<p>当前用户在当前房间累计送礼总数</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IZhuboService.java",
    "groupTitle": "主播API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/zhubo/{zbId}/getGiftUserList/{category}"
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
    "type": "GET",
    "url": "/zhubo/getZbDetail/{zbId}",
    "title": "主播详情",
    "name": "getZbDetail",
    "description": "<blockquote> <p>点击主播头像，进入主播迷你卡，获取主播详情</br></p> </blockquote>",
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
            "type": "String",
            "optional": false,
            "field": "zbNickname",
            "description": "<p>主播昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "signature",
            "description": "<p>用户签名</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "sex",
            "description": "<p>用户性别</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userHeadImg",
            "description": "<p>主播头像</p>"
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
            "type": "Integer",
            "optional": false,
            "field": "userLevel",
            "description": "<p>用户等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "vipLevel",
            "description": "<p>用户VIP等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "isAttention",
            "description": "<p>用户是否关注（0=未关注，1=已关注）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "attentionUserTotal",
            "description": "<p>粉丝</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "diamondGiftZBTotal",
            "description": "<p>收礼</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IZhuboService.java",
    "groupTitle": "主播API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/zhubo/getZbDetail/{zbId}"
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
    "type": "GET",
    "url": "/zhubo/getZhuboList/{num}",
    "title": "主播推荐列表",
    "name": "getZhuboList",
    "description": "<blockquote> <p>点击收藏，没有关注列表，显示热门推荐主播</br> 如果没有登陆，显示热门推荐主播</br> 如果有登陆，显示已关注的主播</br></p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "zbId",
            "description": "<p>主播ID</p>"
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
            "type": "String",
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
            "type": "Integer",
            "optional": false,
            "field": "isAttention",
            "description": "<p>用户是否关注 0 =未关注，1=已关注</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IZhuboService.java",
    "groupTitle": "主播API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/zhubo/getZhuboList/{num}"
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
    "type": "GET",
    "url": "/zhubo/overPlay/{planId}",
    "title": "结束直播",
    "name": "overPlay",
    "description": "<blockquote> <p>点击确认退出，结束直播</br> （1）记录直播视频状态is_video=0</br> （2）调Medie接口删除视频</br></p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "result",
            "description": "<p>退出成功或者失败</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/zb/IZhuboService.java",
    "groupTitle": "主播API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/zhubo/overPlay/{planId}"
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
