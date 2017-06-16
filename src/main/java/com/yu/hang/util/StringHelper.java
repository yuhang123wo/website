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
}
