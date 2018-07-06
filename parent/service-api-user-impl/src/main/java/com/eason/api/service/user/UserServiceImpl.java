package com.eason.api.service.user;

import com.eason.api.mapper.UserCodePoMapper;
import com.eason.api.mapper.UserInfoPoMapper;
import com.eason.api.mapper.VerifyCodeLogMapper;
import com.eason.api.po.user.UserCodePo;
import com.eason.api.po.user.UserInfoPo;
import com.eason.api.po.user.VerifyCodeLogPo;
import com.eason.api.service.user.exception.UserServiceException;
import com.eason.api.service.user.model.CodeConfigModel;
import com.eason.api.service.user.vo.code.UserCodeRequestVo;
import com.eason.api.service.user.vo.code.UserCodeResponseVo;
import com.eason.api.service.user.vo.login.LoginRequestVo;
import com.eason.api.service.user.vo.login.LoginResponseVo;
import com.eason.api.service.user.vo.register.RegisterRequestVo;
import com.eason.api.service.user.vo.register.RegisterResponseVo;
import com.eason.api.utils.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserServiceImpl implements IUserService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserInfoPoMapper userMapper;
    @Autowired
    private VerifyCodeLogMapper verifyCodeLogMapper;
    @Autowired
    private UserCodePoMapper userCodeMapper;
    @Autowired
    private CodeMgrImpl codeMgrImpl;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @Override
    public RegisterResponseVo register(RegisterRequestVo request) throws UserServiceException {
        RegisterResponseVo response = new RegisterResponseVo();
        CodeConfigModel codeConfigModel=codeMgrImpl.getCodeConfigConditional();
        try {
            // 1. 参数验证
            if(StringUtils.isBlank(request.getPhone())) {
                throw new UserServiceException("手机号不能为空");
            } else if (StringUtils.isBlank(request.getPassword())){
                throw new UserServiceException("密码不能为空");
            } else if (StringUtils.isBlank(request.getValidateCode())){
                throw new UserServiceException("验证码不能为空");
            }
            // 2.验证注册码  1:注册 2：重置密码
            HashMap<String, Object> resultCodeMap =codeMgrImpl.verifySendCode(request.getPhone(),1,codeConfigModel.getCode_valid_time());
            boolean b=(boolean)resultCodeMap.get("flag");
            if(!b){
                String msg = (String)resultCodeMap.get("errorMsg");
                throw new UserServiceException(msg);
            }

            // 3.验证用户是否注册
            UserInfoPo userPo=new UserInfoPo();
            userPo.setPhone(request.getPhone());
            userPo = this.userMapper.getUserByAccount(userPo);
            if(userPo != null){
                throw new UserServiceException("用户已经注册");
            }

            // 4.业务验证 messae method overload
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            // 验证码类型 1为注册 2为重置
            hashMap.put("type", 1); //注册
            // 用户手机号 1为未使用,2为已经验证失效,3为已经验证成功
            hashMap.put("phone", request.getPhone());
            hashMap.put("state", 1);
            // 验证码有效时间
            hashMap.put("codeVaildTime", codeConfigModel.getCode_valid_time());
            // 多长时间内失败3次则禁止再进行验证
            hashMap.put("verFailTime", codeConfigModel.getVerFailTime());
            // 如果规定时间内验证失败超过3次，禁止验证多长时间
            hashMap.put("verFialForbidTime", codeConfigModel.getVerFialForbidTime());
            HashMap<String, Object>  resultMap = codeMgrImpl.getUserCodeForDB(hashMap);
            if (resultMap != null) {
                Boolean flag = (Boolean) resultMap.get("flag");
                if (flag) {
                    UserCodePo usersCode = (UserCodePo) resultMap.get("usersCode");
                    VerifyCodeLogPo verCode = new VerifyCodeLogPo();
                    verCode.setPhone(request.getPhone());
                    verCode.setErrorCode(request.getValidateCode());
                    verCode.setTrueCode(usersCode.getCode());
                    verCode.setVerTime(new Date());
                    verCode.setType(1);
                    if (usersCode.getCode() != null && usersCode.getCode().equals(request.getValidateCode())) {
                        //(1)更新user表的注册状态
                        UserInfoPo userInfoPo=new UserInfoPo();
                        userInfoPo.setPhone(request.getPhone());
                        userInfoPo.setPassword(request.getPassword());
                        userInfoPo.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                        userInfoPo.setStatus((byte)1);//-1=封号 0=删除    1=已注册
                        userMapper.insert(userInfoPo);
                        //(2)验证码匹配 0为不匹配 1为匹配
                        verCode.setVerResult(1);
                        //(3)将验证记录插入数据库
                        verifyCodeLogMapper.insertModel(verCode);
                        //(4)将验证码标记为已经使用
                        UserCodePo cd = new UserCodePo();
                        cd.setId(usersCode.getId());
                        cd.setUserId(userInfoPo.getId());
                        cd.setState(3);  //1为未使用,2为已经验证失效,3为已经验证成功
                        cd.setUpdateTime(new Date());
                        userCodeMapper.updateModelById(cd);
                        //(5)验证完毕，清除缓存
                        this.stringRedisTemplate.delete(request.getPhone());
                        response.setResult("用户手机注册成功！");
                        response.setUserId(userInfoPo.getId()); //返回userid
                        //(6)发送推送
//                        pushServiceImpl.pushUserService(userInfoPo.getUserId(), PushTemplatePo.STATUS.REGIST_SUCCESS);
                        return response;
                    } else {
                        // 验证码不匹配 0为不匹配 1为匹配
                        verCode.setVerResult(0);
                        // 将验证记录插入数据库
                        verifyCodeLogMapper.insertModel(verCode);
                        throw new UserServiceException("验证码不匹配");
                    }
                } else {
                   throw new UserServiceException((String) resultMap.get("errorMsg"));
                }
            } else {
                // 无法获取有效的验证码
                throw new UserServiceException("无法获取有效的验证码");
            }

        } catch (Exception e) {
            log.error("对用户的验证码进行校验时出错,userPhone:" + request.getPhone(), e);
            throw new UserServiceException(e.getMessage());
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @Override
    public LoginResponseVo login(LoginRequestVo loginRequestVo) throws UserServiceException {
        LoginResponseVo response = new LoginResponseVo();
        String result = null;
        String msg = null;
        try {
            // ①参数验证
            if (StringUtils.isBlank(loginRequestVo.getUsername())) {
                throw  new UserServiceException("用户名不能为空");
            }
            if (StringUtils.isBlank(loginRequestVo.getPassword())) {
                throw  new UserServiceException("密码不能为空");
            }

            // ②验证用户
            UserInfoPo userPo=new UserInfoPo();
            userPo.setPhone(loginRequestVo.getUsername());
            userPo.setPassword(loginRequestVo.getPassword());
            userPo = this.userMapper.getUserByAccount(userPo);
            if (userPo==null) {
                throw new UserServiceException("用户名或密码错误");
            }

            if (!userPo.getPassword().equals(loginRequestVo.getPassword())) {
                throw new UserServiceException("用户密码错误");
            }
            String token = TokenUtil.getToken();
            response.setToken(token);
            stringRedisTemplate.boundHashOps("user_api_token").put(userPo.getId(),token);

            //3更新用户登陆时间
            userPo.setUpdatedAt(new Date());
            userPo.setApiToken(token);
            this.userMapper.updateByPrimaryKey(userPo);

            response.setUserId(userPo.getId());
            response.setNickname(userPo.getNickname());
            response.setUsername(userPo.getUsername());
            response.setAvatar(userPo.getAvatar());
            return response;
        } catch (UserServiceException e) {
            throw new UserServiceException(e.getMessage());
        }
    }

    @RequestMapping(value = "/reset",method = RequestMethod.POST)
    @Override
    public RegisterResponseVo reset(RegisterRequestVo request) throws UserServiceException {
        RegisterResponseVo response = new RegisterResponseVo();
        String result = null;
        String msg = null;
        try {
            // 1. 参数验证
            if (StringUtils.isBlank(request.getValidateCode())) {
                throw new UserServiceException("验证码不能为空");
            } else if (StringUtils.isBlank(request.getPhone())) {
                throw new UserServiceException("手机号不能为空");
            } else if (StringUtils.isBlank(request.getPassword())){
                throw new UserServiceException("重置密码不能为空");
            }

            // 业务验证 messae method overload
            HashMap<String, Object> hashMap = codeMgrImpl.getVerifCondition();
            // 验证码类型
            hashMap.put("type", 2); //2=重置
            // 用户手机号
            hashMap.put("phone", request.getPhone());
            hashMap.put("state", 1);
            HashMap<String, Object>  resultMap = codeMgrImpl.getUserCodeForDB(hashMap);
            if (resultMap != null) {
                Boolean flag = (Boolean) resultMap.get("flag");
                UserInfoPo userPo=new UserInfoPo();
                userPo.setPhone(request.getPhone());
                UserInfoPo userInfo = this.userMapper.getUserByAccount(userPo);
                if(userInfo ==null){
                    // 此用户不存在
                    throw new UserServiceException("该用户不存在");
                }
                if (flag && userInfo!=null) {
                    UserCodePo usersCode = (UserCodePo) resultMap.get("usersCode");
                    VerifyCodeLogPo verCode = new VerifyCodeLogPo();
                    verCode.setPhone(request.getPhone());
                    verCode.setErrorCode(request.getValidateCode());
                    verCode.setTrueCode(usersCode.getCode());
                    verCode.setVerTime(new Date());
                    verCode.setType(2);
                    if (usersCode.getCode() != null && usersCode.getCode().equals(request.getValidateCode())) {
                        //(1)更新user表的密码
                        userInfo.setPassword(request.getPassword());
                        userInfo.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                        this.userMapper.updateByPrimaryKey(userInfo);
                        //(2)验证码 匹配=1 不匹配=0
                        verCode.setVerResult(1);
                        //(3)将验证码标记为已经使用 1为未使用,2为已经验证失效,3为已经验证成功
                        UserCodePo cd = new UserCodePo();
                        cd.setId(usersCode.getId());
                        cd.setUserId(userPo.getId());
                        cd.setState(3);
                        cd.setUpdateTime(new Date());
                        userCodeMapper.updateModelById(cd);
                        //(4)将验证记录插入数据库
                        verifyCodeLogMapper.insertModel(verCode);
                        //(5)验证完毕，清除缓存
                        this.stringRedisTemplate.delete(request.getPhone());
                        response.setResult("用户重置密码成功！");
                        response.setUserId(userPo.getId()); //返回userid
                        return response;
                    } else {
                        // 验证码不匹配 0为不匹配 1为匹配
                        verCode.setVerResult(0);
                        // 将验证记录插入数据库
                        verifyCodeLogMapper.insertModel(verCode);
                        throw new UserServiceException("验证码不匹配");
                    }
                } else {
                    throw new UserServiceException((String) resultMap.get("errorMsg"));
                }
            } else {
                // 无法获取有效的验证码
                throw new UserServiceException("无法获取有效的验证码");
            }

        } catch (Exception e) {
            log.error("重置密码时出错,userPhone:" + request.getPhone(), e);
            throw new UserServiceException(e.getMessage());
        }
    }

    @RequestMapping(value = "/getValidateCode",method = RequestMethod.POST)
    @Override
    public UserCodeResponseVo getValidateCode(UserCodeRequestVo request) throws UserServiceException {
        UserCodeResponseVo getUserCodeResponse = new UserCodeResponseVo();
        try {
            if (StringUtils.isBlank(request.getPhone())) {
                throw  new UserServiceException("手机号不能为空");
            } else if (request.getCodeType() == null) {
                throw  new UserServiceException("验证类型不能为空");
            } else if (!request.getCodeType().equals("1") && !request.getCodeType().equals("2")) {
                throw  new UserServiceException("验证类型只能为1注册或者2重置");
            }
            // 2. 业务验证
            // 根据手机号查询此用户是否存在
            UserInfoPo userPo=new UserInfoPo();
            userPo.setPhone(request.getPhone());
            userPo = this.userMapper.getUserByAccount(userPo);
            // 如果是注册
            if (String.valueOf(request.getCodeType()).equals(1)) {
                if (userPo != null) {
                    // 该用户已经注册
                    throw  new UserServiceException("该用户已经注册");
                } else {
                    // 获取验证的条件
                    HashMap<String, Object>  hashmap= codeMgrImpl.getVerifCondition();
                    // 判断是否具有发送验证码的权限
                    HashMap<String, Object> conformMap = codeMgrImpl.isConform(hashmap);
                    boolean flag = (boolean) conformMap.get("flag");
                    // 符合发送验证码的要求，发送验证码
                    if (flag) {
                        // 判断此用户最后一次发送的验证码的状态
                        codeMgrImpl.updateLastCodeStatus(hashmap);
                        // 发送验证码
                        String code=codeMgrImpl.createCodeAndSend(request);
                        getUserCodeResponse.setCode(code);
                        if (code==null) {
                            throw new UserServiceException("发送验证码失败");
                        }
                    } else {
                        throw new UserServiceException( (String) conformMap.get("errorMsg"));
                    }
                }
                // 重置密码
            } else if (String.valueOf(request.getCodeType()).equals(String.valueOf(2))) {
                if (userPo != null) {
                    // 获取验证的条件
                    HashMap<String, Object> hashmap = codeMgrImpl.getVerifCondition();
                    // 判断是否具有发送验证码的权限
                    HashMap<String, Object> conformMap = codeMgrImpl.isConform(hashmap);
                    boolean flag = (boolean) conformMap.get("flag");
                    // 符合发送验证码的要求，发送验证码
                    if (flag) {
                        // 判断此用户最后一次发送的验证码的状态
                        codeMgrImpl.updateLastCodeStatus(hashmap);
                        // 发送验证码
                        String code=codeMgrImpl.createCodeAndSend(request);
                        getUserCodeResponse.setCode(code);
                        if (code==null) {
                            throw new UserServiceException("发送验证码失败");
                        }
                    } else {
                        // 不符合标准，无法发送验证码
                        throw new UserServiceException( (String) conformMap.get("errorMsg"));
                    }
                } else {
                    // 手机号不存在
                    throw new UserServiceException( "手机号不存在，该用户未注册");
                }
            }
            getUserCodeResponse.setResult("验证码发送成功");
            return getUserCodeResponse;
        } catch (Exception e) {
            log.error("重置密码或者注册用户前检验用户时出错,userPhone:" + request.getPhone(),e);
            throw new UserServiceException(e.getMessage());
        }
    }

}
