package com.eason.api.utils;

public class PushUtil {
    /**
     * 发送文本验证码
     */
    public static boolean sendTextCode(String code, String phone){
//		log.info("发送文本验证码" + code);
//		if(messageSource != null){
//			ms = messageSource;
//		}
//		String address = "120.76.25.160";//远程地址：不带http://
//		int port = 7788;//远程端口
//		String account = "xxll";//账户
//		String token = "520620";//token
//		String mobile = phone;//发送手机号
//		String body = "【卓尔冷链】验证码："+code;//短信内容
//		int userId=186;//用户Id
//		KXTSmsSDK kxtsms = new KXTSmsSDK();
//		kxtsms.init(address, port, account, token,userId);
//		try{
//			body = URLEncoder.encode(body,"UTF-8");//URL编码 UTF-8方式
//			String result = kxtsms.send(mobile,body);
//			JAXBContext context = JAXBContext.newInstance(KXTSMSVo.class);
//	        Unmarshaller unmarshaller = context.createUnmarshaller();
//	        KXTSMSVo vo = (KXTSMSVo)unmarshaller.unmarshal(new StringReader(result));
//	        System.out.println(vo.getReturnstatus());
//	        System.out.println(vo.getSuccessCounts());
//	        if("Success".equals(vo.getReturnstatus())){
//	        	return true;
//	        }else{
//	        	log.error("错误码=" + vo.getReturnstatus() +" 错误信息= "+vo.getMessage());
//	        	return false;
//	        }
//		}catch (Exception e) {
//			log.error("发送验证码异常",e);
//		}
        return false;
    }
}
