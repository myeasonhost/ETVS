package com.eason.api.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.util.UUID;

public class TokenUtil {

	private final static Log logger = LogFactory.getLog(TokenUtil.class);

	/**
	 * @FuncName getToken
	 * @description 获取token
	 * @return strong token
	 * @throws Exception
	 */
	public static String getToken() {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(UUID.randomUUID().toString().replaceAll("-", "").getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}

				buf.append(Integer.toHexString(i));
			}

		} catch (Exception e) {
			logger.error("生成Token异常" + e);
		}
		return buf.toString();
	}

}
