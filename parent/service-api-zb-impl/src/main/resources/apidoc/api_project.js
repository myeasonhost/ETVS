define({
  "name": "直播部分系统架构API",
  "version": "1.0.0",
  "description": "本API文档由Java技术开发，只支持直播部分系统的相关客户端Android/IOS使用",
  "title": "直播系统架构API文档",
  "url": "http://192.168.0.138:8769/api",
  "sampleUrl": "http://192.168.0.138:8769/api",
  "header": {
    "title": "版本迭代记录",
    "content": "<table>\n<thead>\n<tr>\n<th style=\"text-align:left\">日期</th>\n<th style=\"text-align:left\">操作人</th>\n<th style=\"text-align:left\">模块</th>\n<th style=\"text-align:left\">API名称</th>\n<th style=\"text-align:left\">修订记录</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td style=\"text-align:left\">2017/11/11</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">用户API</td>\n<td style=\"text-align:left\">关注/取消关注</td>\n<td style=\"text-align:left\">新增渠道字段，用于后台统计；API调整：/user/{userId}/<strong>{channel}</strong>/isAttention/{zbId}/{isAttention}</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/11</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">直播首页API</td>\n<td style=\"text-align:left\">房间列表</td>\n<td style=\"text-align:left\">新增分页返回总数<strong>total</strong>和总行数<strong>rows</strong>字段</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/14</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">三方平台API</td>\n<td style=\"text-align:left\">获取列表IM/Media地址</td>\n<td style=\"text-align:left\">新增主播id，用于auth认证；API调整：/api/platform/im/get/<strong>{zbId}</strong>；修改端口字段调整Integer类型</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/15</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">房间API</td>\n<td style=\"text-align:left\">房间是否收费</td>\n<td style=\"text-align:left\">新增返回用户试看信息，是否试看，试看时间等</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/15</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">直播首页API</td>\n<td style=\"text-align:left\">房间列表</td>\n<td style=\"text-align:left\">新增userId字段，用于获取当前用户收藏列表；API调整：/api/index/{category}/getIndexList/{position}/{pageSize}/<strong>{userId}</strong></td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/16</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">直播首页API</td>\n<td style=\"text-align:left\">房间列表</td>\n<td style=\"text-align:left\">删除userId字段，从token中获取；新增场次roomPlanId返回值，API调整：/api/index/{category}/getIndexList/{position}/{pageSize}</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/16</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">房间API</td>\n<td style=\"text-align:left\">房间是否收费</td>\n<td style=\"text-align:left\">删除userId字段，从token中获取；API调整：/room/{roomId}/isCharged</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/17</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">用户API</td>\n<td style=\"text-align:left\">关注/取消关注</td>\n<td style=\"text-align:left\">修改zbId字段，支持一键关注格式；API调整：/user/{channel}/isAttention/<strong>{zbIds}</strong>/{isAttention}</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/17</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">直播首页API</td>\n<td style=\"text-align:left\">房间列表</td>\n<td style=\"text-align:left\">新增返回值zbHeadImg、zbLevel字段，用户主播推荐</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/17</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">主播API</td>\n<td style=\"text-align:left\">主播列表</td>\n<td style=\"text-align:left\">用户主播推荐，删除userId字段，从token中获取；去掉不必要的字段</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/17</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">房间API</td>\n<td style=\"text-align:left\">进入房间</td>\n<td style=\"text-align:left\">修改用户等级字段类型String-&gt;Integer</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/17</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">房间API</td>\n<td style=\"text-align:left\">进入房间</td>\n<td style=\"text-align:left\">修改所有的场次planId字段类型String-&gt;Integer，用于与原点播系统统一</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/18</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">主播API</td>\n<td style=\"text-align:left\">开始直播</td>\n<td style=\"text-align:left\">删除userId字段，从token中获取；除去请求参数roomId，通过后台zdId获取</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/18</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">主播API</td>\n<td style=\"text-align:left\">开始直播</td>\n<td style=\"text-align:left\">修改startTime字段，所有的时间请传递Long类型的时间戳，修改持续时间设置String-&gt;Integer</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/18</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">主播API</td>\n<td style=\"text-align:left\">获取开播信息</td>\n<td style=\"text-align:left\">重做此接口返回值</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/18</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">主播API</td>\n<td style=\"text-align:left\">开始直播</td>\n<td style=\"text-align:left\">重做此接口返回值</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/20</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">主播API</td>\n<td style=\"text-align:left\">关于价格类型</td>\n<td style=\"text-align:left\">所有的价格类型，全部修改未Double类型</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/20</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">房间API</td>\n<td style=\"text-align:left\">获取房间属性设置</td>\n<td style=\"text-align:left\">该接口暂时废弃，所有的房间属性初始化数据，从开播准备接口获取</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/20</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">房间API</td>\n<td style=\"text-align:left\">设置房间直播封面</td>\n<td style=\"text-align:left\">本测试平台不支持，测试文件上传接口，请使用Restclient等文件上传工具</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/20</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">用户API</td>\n<td style=\"text-align:left\">关注/取消关注</td>\n<td style=\"text-align:left\">修改zbId-&gt;userIds字段，支持一键关注格式(用户或者主播)；API调整：/user/{channel}/isAttention/<strong>{userIds}</strong>/{isAttention}</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/20</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">主播API</td>\n<td style=\"text-align:left\">主播详情（迷你卡）</td>\n<td style=\"text-align:left\">删除userId字段，从token中获取；API调整；用户等级String-&gt;Integer</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/24</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">三方平台API</td>\n<td style=\"text-align:left\">获取列表IM/Media地址</td>\n<td style=\"text-align:left\">新增token，用于auth认证；</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/24</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">主播API</td>\n<td style=\"text-align:left\">获取开播信息</td>\n<td style=\"text-align:left\">返回值重做，请仔细看接口说明</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/24</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">房间API</td>\n<td style=\"text-align:left\">房间是否收费</td>\n<td style=\"text-align:left\">新增返回值timeInterval，用于收费间隔</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/11/27</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">房间API</td>\n<td style=\"text-align:left\">退出房间</td>\n<td style=\"text-align:left\">新增统计相关的返回值，请仔细看接口说明</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/12/27</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">主播API</td>\n<td style=\"text-align:left\">主播迷你卡</td>\n<td style=\"text-align:left\">新增zbUserLevel、zbUserVIP字段</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2017/12/27</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">用户API</td>\n<td style=\"text-align:left\">用户迷你卡</td>\n<td style=\"text-align:left\">新增用户迷你卡API，请仔细看接口说明</td>\n</tr>\n<tr>\n<td style=\"text-align:left\">2018/3/23</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">管理平台API</td>\n<td style=\"text-align:left\">后台开播API</td>\n<td style=\"text-align:left\">获取开播信息+开播接口，运营账号登陆后台，创建自定义直播房间界面业务流程</td>\n</tr>\n<tr>\n<td style=\"text-align:left\"></td>\n<td style=\"text-align:left\"></td>\n<td style=\"text-align:left\"></td>\n<td style=\"text-align:left\"></td>\n<td style=\"text-align:left\"></td>\n</tr>\n</tbody>\n</table>\n"
  },
  "order": [
    "Error",
    "Define",
    "PostTitleAndError",
    "PostError"
  ],
  "withGenerator": false,
  "defaultVersion": "0.0.0",
  "apidoc": "0.3.0",
  "generator": {
    "name": "apidoc",
    "time": "2018-07-13T05:35:55.050Z",
    "url": "http://apidocjs.com",
    "version": "0.17.6"
  }
});
