package com.yu.hang.util;

import java.util.List;

/**
 * 工具类
 * 
 * @author yuhang
 * @Date 2017年6月8日
 * @desc
 */
public class StringHelper {

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean objectIsNull(Object obj) {
		return obj == null ? true : false;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean objectIsNotNull(Object obj) {
		return !objectIsNull(obj);
	}

	/**
	 * 验证集合是否为空
	 * 
	 * @param list
	 * @return
	 */
	public static boolean listIsNotNull(List<?> list) {
		return list != null && list.size() > 0 ? true : false;
	}

	/**
	 * 
	 * @param str
	 * @return 下午4:35:40 boolean
	 */
	public static boolean isNull(String str) {
		if (str == null || "".equals(str.trim()))
			return true;
		return false;
	}

	/**
	 * 
	 * @param str
	 * @return 下午4:36:45 boolean
	 */
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	/**
	 * 
	 * @param str
	 * @return String
	 */
	public static String trimString(String str) {
		if (isNull(str))
			return null;
		return str.trim();
	}

	/**
	 * 
	 * @param str
	 * @return 下午4:40:44 String
	 */
	public static String addLike(String str) {
		if (isNull(str))
			return null;
		return "%" + str.trim() + "%";
	}

	/**
	 * 
	 * @param str
	 * @return String
	 */
	public static String getKString(String str) {
		if (isNull(str))
			return "";
		return str.trim();
	}
}
