define({ "api": [
  {
    "version": "1.0.0",
    "group": "usercode",
    "permission": [
      {
        "name": "API"
      }
    ],
    "name": "getCodeConfigConditional",
    "description": "<blockquote> <p>验证码管理API - 获取验证码配置条件\t</br> 验证码配置属性</br> （1）oneDayNum=10：一天发送验证码的总量</br> （2）totalNum=100：总共发送验证码的总量</br> （3）lastTime=3：30分钟内只能发送3次</br> （4）interval_time=1：过多少（分）可以重新发送验证码</br> （5）code_valid_time=10：验证码失效时间（分） </br> （6）verFailTime=3：多长时间内失败3次则禁止再进行验证 </br> （7）verFialForbidTime=10：如果规定时间内验证失败超过3次，禁止验证多长时间 </br></p> </blockquote>",
    "type": "",
    "url": "",
    "filename": "src/main/java/com/eason/api/service/user/ICodeService.java",
    "groupTitle": "验证码管理API"
  },
  {
    "version": "1.0.0",
    "group": "user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/edit",
    "title": "用户信息更新",
    "name": "edit",
    "description": "<blockquote> <p>用户接入API - 用户信息更新</br> 用户信息更新接口</br> （1）验证参数：是否合法</br> （2）支持昵称更新</br> （3）支持性别更新</br> （4）支持生日更新</br> （5）支持个性签名更新</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "nickname",
            "description": "<p>昵称（可选）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "sex",
            "description": "<p>性别（可选）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "birthday",
            "description": "<p>生日（可选）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "signature",
            "description": "<p>签名（可选）</p>"
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
            "description": "<p>用户id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "result",
            "description": "<p>更新信息</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/service/user/IUserService.java",
    "groupTitle": "用户接入API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/user/edit"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/getValidateCode",
    "title": "获取用户验证码",
    "name": "getValidateCode",
    "description": "<blockquote> <p>用户接入API - 获取验证码</br> 获取验证码接口</br> （1）验证参数：是否合法</br> A .  验证phone是否注册</br> （2）生成验证码逻辑</br> （3）实现验证码-消息推送（短信实现）</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "codeType",
            "description": "<p>验证码类型1为注册 2为忘记密码</p>"
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
            "field": "code",
            "description": "<p>用户验证码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "result",
            "description": "<p>注册信息</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/service/user/IUserService.java",
    "groupTitle": "用户接入API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/user/getValidateCode"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/login",
    "title": "用户登陆",
    "name": "login",
    "description": "<blockquote> <p>用户管理API - 用户登陆\t</br> 用户登陆接口</br> （1）验证参数：是否合法</br> A .  验证username是否存在</br> B .  验证password是否错误</br> （2）登陆逻辑判断</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>用户账号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>用户密码</p>"
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
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>登陆用户名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "nickname",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "avatar",
            "description": "<p>用户头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/service/user/IUserService.java",
    "groupTitle": "用户接入API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/user/login"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/register",
    "title": "用户注册",
    "name": "register",
    "description": "<blockquote> <p>用户接入API - 用户注册\t</br> 用户注册接口</br> （1）验证参数：是否合法</br> A .  验证phone是否注册</br> B .  验证validateCode错误次数限制、验证码重试次数、是否正确等</br> （2）注册逻辑</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>用户密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "validateCode",
            "description": "<p>验证码</p>"
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
            "type": "String",
            "optional": false,
            "field": "result",
            "description": "<p>注册信息</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/service/user/IUserService.java",
    "groupTitle": "用户接入API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/user/register"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/reset",
    "title": "用户密码重置",
    "name": "reset",
    "description": "<blockquote> <p>用户接入API - 用户密码重置\t</br> 用户密码重置接口</br> （1）验证参数：是否合法</br> A .  验证phone是否注册</br> B .  验证validateCode错误次数限制、验证码重试次数、是否正确等</br> （2）注册逻辑</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>用户密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "validateCode",
            "description": "<p>验证码</p>"
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
            "type": "String",
            "optional": false,
            "field": "result",
            "description": "<p>注册信息</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/api/service/user/IUserService.java",
    "groupTitle": "用户接入API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/user/reset"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/uploadAvatar",
    "title": "上传用户头像",
    "name": "uploadAvatar",
    "description": "<blockquote> <p>进入主播开播界面，设置直播房间封面</br> 使用postman的选项的form-data的key=avatar 文件类型 可以测试</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "byte[]",
            "optional": false,
            "field": "avatar",
            "description": "<p>用户头像</p>"
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
    "filename": "src/main/java/com/eason/api/service/user/IUserService.java",
    "groupTitle": "用户接入API",
    "sampleRequest": [
      {
        "url": "http://192.168.0.138:8769/api/user/uploadAvatar"
      }
    ]
  }
] });
