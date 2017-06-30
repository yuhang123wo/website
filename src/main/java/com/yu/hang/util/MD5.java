package com.yu.hang.util;

import java.security.MessageDigest;

/**
 * MD5 32位加密
 * 
 * @author duyu
 * 
 */
public class MD5 {
	/***
	 * MD5加密 生成32位md5码
	 * 
	 * @param
	 * @return 返回32位md5码
	 */
	public static String md5Encode(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}
}
