define({
  "name": "直播部分系统架构API",
  "version": "1.0.0",
  "description": "本API文档由Java技术开发，只支持直播部分系统的相关客户端Android/IOS使用",
  "title": "直播系统架构API文档",
  "url": "http://192.168.0.138:8769/api",
  "sampleUrl": "http://192.168.0.138:8769/api",
  "header": {
    "title": "版本迭代记录",
    "content": "<table>\n<thead>\n<tr>\n<th style=\"text-align:left\">日期</th>\n<th style=\"text-align:left\">操作人</th>\n<th style=\"text-align:left\">模块</th>\n<th style=\"text-align:left\">API名称</th>\n<th style=\"text-align:left\">修订记录</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td style=\"text-align:left\">2017/11/11</td>\n<td style=\"text-align:left\">EASON</td>\n<td style=\"text-align:left\">用户API</td>\n<td style=\"text-align:left\">关注/取消关注</td>\n<td style=\"text-align:left\">新增渠道字段，用于后台统计；API调整：/user/{userId}/<strong>{channel}</strong>/isAttention/{zbId}/{isAttention}</td>\n</tr>\n</tbody>\n</table>\n"
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
    "time": "2018-07-10T07:47:36.688Z",
    "url": "http://apidocjs.com",
    "version": "0.17.6"
  }
});
