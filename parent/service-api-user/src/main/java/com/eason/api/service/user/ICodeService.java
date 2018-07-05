package com.eason.api.service.user;

import com.eason.api.service.user.exception.UserServiceException;
import com.eason.api.service.user.model.CodeConfigModel;
import com.eason.api.service.user.vo.code.UserCodeRequestVo;
import com.eason.api.service.user.vo.code.UserCodeResponseVo;
import com.eason.api.service.user.vo.login.LoginRequestVo;
import com.eason.api.service.user.vo.login.LoginResponseVo;
import com.eason.api.service.user.vo.register.RegisterRequestVo;
import com.eason.api.service.user.vo.register.RegisterResponseVo;

/**
 * @apiDefine usercode 验证码管理API
 */
public interface ICodeService {

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup usercode
	 * @apiPermission API
	 * @apiName getCodeConfigConditional
	 *
	 * @apiDescription
	 * >	验证码管理API - 获取验证码配置条件	</br>
	 * >	验证码配置属性</br>
	 *>（1）oneDayNum=10：一天发送验证码的总量</br>
	 *>（2）totalNum=100：总共发送验证码的总量</br>
	 *>（3）lastTime=3：30分钟内只能发送3次</br>
	 *>（4）interval_time=60：过多少（秒）可以重新发送验证码</br>
	 *>（5）code_valid_time=600：验证码失效时间（秒） </br>
	 *>（6）verFailTime=3：多长时间内失败3次则禁止再进行验证 </br>
	 *>（7）verFialForbidTime=600：如果规定时间内验证失败超过3次，禁止验证多长时间 </br>
	 */
	public CodeConfigModel getCodeConfigConditional() throws UserServiceException;



}