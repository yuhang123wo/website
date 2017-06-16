package com.yu.hang.util;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * @author yuhang
 * @Date 2017年6月8日
 * @desc
 */
public class ReflectionHelper {

	/**
	 * 获取泛型参数类型
	 * 
	 * @param clazz
	 *            类类型
	 * @param <T>
	 *            类类型
	 * @return 对象类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClassGenricType(@SuppressWarnings("rawtypes") final Class clazz) {
		return getClassGenricType(clazz, 0);
	}

	/**
	 * 获取泛型参数类型
	 * 
	 * @param clazz
	 *            类类型
	 * @param index
	 *            参数索引
	 * @return 类类型
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	/**
	 * 获取方法对象
	 * 
	 * @param clazz
	 *            类类型
	 * @param methodName
	 *            方法名
	 * @param parameterTypes
	 *            类型
	 * @return 方法
	 */
	public static Method getDeclaredMethod(Class<?> clazz, String methodName,
			Class<?>... parameterTypes) {
		Method method = null;

		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				return method;
			} catch (Exception e) {
			}
		}

		return null;
	}

}
