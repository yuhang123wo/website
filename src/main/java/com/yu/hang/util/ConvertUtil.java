package com.yu.hang.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class ConvertUtil {
	/**
	 * 
	 * @Description 对象克隆
	 */
	public static <K> K beanClone(K sourceObj, Class<K> sourceClass) {

		if (sourceObj == null) {
			return null;

		}
		K targetObj = null;

		try {
			Constructor<K> voconstructor = sourceClass.getConstructor(new Class[] {});
			targetObj = voconstructor.newInstance(new Object[] {});
			BeanUtils.copyProperties(sourceObj, targetObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return targetObj;
	}

	/**
	 * 
	 * @Description 深度克隆
	 */
	@SuppressWarnings("unchecked")
	public static <K> K depthClone(K sourceObj) {

		K targetObj = null;

		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(sourceObj);// 源对象

			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(bi);
			targetObj = (K) oi.readObject();// 目标对象
		} catch (Exception e) {
			e.printStackTrace();
		}

		return targetObj;
	}

	/**
	 * 
	 * @Description 把一个列表中的PO类全转换成为相应的VO类的列表，VO类需要有一个以PO类为参数的构造函数
	 * @param sourceList
	 *            原PO列表
	 * @param poclass
	 *            原始的PO类
	 * @param voclass
	 *            需要转换成的VO类
	 * @return 转换成的VO列表
	 */
	public static <K, V> List<V> convertPOListToVOList(List<K> sourceList, Class<K> poclass,
			Class<V> voclass) {

		if (sourceList == null) {
			return null;

		}

		List<V> targetList = new ArrayList<V>();

		try {
			if (sourceList.size() > 0) {
				Constructor<V> voconstructor = voclass.getConstructor(new Class[] { poclass });

				for (K source : sourceList) {
					V target = voconstructor.newInstance(new Object[] { source });
					targetList.add(target);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return targetList;
	}

	public static <K, V> List<V> convertVOListToPOList(List<K> sourceList, Class<K> voclass,
			Class<V> poclass) {

		List<V> returnlist = new ArrayList<V>();

		String poname = poclass.getName();
		String shortname = poname.substring(poname.lastIndexOf(".") + 1);

		Method[] methods = voclass.getMethods();
		Method targetmethod = null;
		for (Method method : methods) {
			if (shortname.equals(method.getName().replaceAll("to", ""))) {
				targetmethod = method;
				break;
			}
		}

		if (targetmethod != null) {
			for (K vo : sourceList) {
				V po = null;
				try {
					po = (V) targetmethod.invoke(vo, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				returnlist.add(po);
			}
		}

		return returnlist;
	}

}
