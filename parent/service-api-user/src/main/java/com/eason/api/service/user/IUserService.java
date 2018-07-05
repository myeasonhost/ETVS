package com.eason.api.service.user;

import com.eason.api.service.user.exception.UserServiceException;
import com.eason.api.service.user.vo.code.UserCodeRequestVo;
import com.eason.api.service.user.vo.code.UserCodeResponseVo;
import com.eason.api.service.user.vo.login.LoginRequestVo;
import com.eason.api.service.user.vo.login.LoginResponseVo;
import com.eason.api.service.user.vo.register.RegisterRequestVo;
import com.eason.api.service.user.vo.register.RegisterResponseVo;

/**
 * @apiDefine user 用户接入API
 */
public interface IUserService {

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup user
	 * @apiPermission Android/IOS
	 * @api {POST} /user/register 用户注册
	 * @apiName register
	 *
	 * @apiDescription
	 * >	用户接入API - 用户注册	</br>
	 * >用户注册接口</br>
	 * >（1）验证参数：是否合法</br>
	 * >     A .  验证phone是否注册</br>
	 * >     B .  验证validateCode错误次数限制、验证码重试次数、是否正确等</br>
	 * >（2）注册逻辑</br>
	 *
	 * @apiSuccess {String} phone  手机号
	 * @apiSuccess {String} password 	 用户密码
	 * @apiSuccess {String} validateCode 	 验证码
	 *
	 * @apiSuccess {Integer} userId  用户ID
	 * @apiSuccess {String} result  注册信息
	 *
	 */
	public RegisterResponseVo register(RegisterRequestVo registerRequestVo) throws UserServiceException;

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup user
	 * @apiPermission Android/IOS
	 * @api {POST} /user/login 用户登陆
	 * @apiName login
	 *
	 * @apiDescription
	 * >	用户管理API - 用户登陆	</br>
	 * >用户登陆接口</br>
	 * >（1）验证参数：是否合法</br>
	 * >     A .  验证username是否存在</br>
	 * >     B .  验证password是否错误</br>
	 * >（2）登陆逻辑判断</br>
	 *
	 * @apiSuccess {String} username  用户账号
	 * @apiSuccess {String} password 	 用户密码
	 *
	 * @apiSuccess {Integer} userId  用户ID
	 * @apiSuccess {String} username  登陆用户名
	 * @apiSuccess {String} nickname 用户昵称
	 * @apiSuccess {String} avatar  用户头像
	 *@apiSuccess {String} token  用户token
	 *
	 */
	public LoginResponseVo login(LoginRequestVo loginRequestVo) throws UserServiceException;


	/**
	 * @apiVersion 1.0.0
	 * @apiGroup user
	 * @apiPermission Android/IOS
	 * @api {POST} /user/reset 用户密码重置
	 * @apiName register
	 *
	 * @apiDescription
	 * >	用户接入API - 用户密码重置	</br>
	 * >用户密码重置接口</br>
	 * >（1）验证参数：是否合法</br>
	 * >     A .  验证phone是否注册</br>
	 * >     B .  验证validateCode错误次数限制、验证码重试次数、是否正确等</br>
	 * >（2）注册逻辑</br>
	 *
	 * @apiSuccess {String} phone  手机号
	 * @apiSuccess {String} password 	 用户密码
	 * @apiSuccess {String} validateCode 	 验证码
	 *
	 * @apiSuccess {Integer} userId  用户ID
	 * @apiSuccess {String} result  注册信息
	 *
	 */
	public RegisterResponseVo reset(RegisterRequestVo registerRequestVo) throws UserServiceException;



	/**
	 * @apiVersion 1.0.0
	 * @apiGroup user
	 * @apiPermission Android/IOS
	 * @api {POST} /user/getValidateCode 获取用户验证码
	 * @apiName getValidateCode
	 *
	 * @apiDescription
	 * >	用户接入API - 获取验证码</br>
	 * >获取验证码接口</br>
	 * >（1）验证参数：是否合法</br>
	 * >     A .  验证phone是否注册</br>
	 * >（2）生成验证码逻辑</br>
	 *>（3）实现验证码-消息推送（短信实现）</br>
	 *
	 * @apiSuccess {String} phone  手机号
	 *
	 * @apiSuccess {String} code  用户验证码
	 * @apiSuccess {String} result  注册信息
	 *
	 */
	public UserCodeResponseVo getValidateCode(UserCodeRequestVo userCodeRequestVo) throws UserServiceException;


}
