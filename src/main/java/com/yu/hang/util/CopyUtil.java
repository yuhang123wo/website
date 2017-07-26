package com.yu.hang.util;

import java.beans.PropertyDescriptor;
import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yuhang
 * @Date 2017年7月26日
 * @desc
 */
public class CopyUtil {

	private static Logger logger = LoggerFactory.getLogger(CopyUtil.class);

	/**   */
	/**
	 * Copy properties of orig to dest Exception the Entity and Collection Type
	 * 
	 * @param <K>
	 * 
	 * @param dest
	 * @param orig
	 * @return the dest bean
	 */
	public static <K, T> K copyProperties(K dest, T orig) {
		if (dest == null || orig == null) {
			return dest;
		}

		PropertyDescriptor[] destDesc = PropertyUtils.getPropertyDescriptors(dest);
		try {
			for (int i = 0; i < destDesc.length; i++) {
				Class<?> destType = destDesc[i].getPropertyType();
				Class<?> origType = PropertyUtils.getPropertyType(orig, destDesc[i].getName());
				if (destType != null && destType.equals(origType) && !destType.equals(Class.class)) {
					if (!Collection.class.isAssignableFrom(origType)) {
						try {
							Object value = PropertyUtils.getProperty(orig, destDesc[i].getName());
							PropertyUtils.setProperty(dest, destDesc[i].getName(), value);
						} catch (Exception ex) {
							logger.error("copy element exception{}", ex);
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error("copyPropertis exception{}", ex);
		}
		return dest;
	}

	/**   */
	/**
	 * Copy properties of orig to dest Exception the Entity and Collection Type
	 * 
	 * @param dest
	 * @param orig
	 * @param ignores
	 * @return the dest bean
	 */
	public static <K, T> K copyProperties(K dest, T orig, String[] ignores) {
		if (dest == null || orig == null) {
			return dest;
		}

		PropertyDescriptor[] destDesc = PropertyUtils.getPropertyDescriptors(dest);
		try {
			for (int i = 0; i < destDesc.length; i++) {
				if (contains(ignores, destDesc[i].getName())) {
					continue;
				}

				Class<?> destType = destDesc[i].getPropertyType();
				Class<?> origType = PropertyUtils.getPropertyType(orig, destDesc[i].getName());
				if (destType != null && destType.equals(origType) && !destType.equals(Class.class)) {
					if (!Collection.class.isAssignableFrom(origType)) {
						Object value = PropertyUtils.getProperty(orig, destDesc[i].getName());
						PropertyUtils.setProperty(dest, destDesc[i].getName(), value);
					}
				}
			}
		} catch (Exception ex) {
			logger.error("copyPropertis exception{}", ex);
		}
		return dest;
	}

	static boolean contains(String[] ignores, String name) {
		boolean ignored = false;
		for (int j = 0; ignores != null && j < ignores.length; j++) {
			if (ignores[j].equals(name)) {
				ignored = true;
				break;
			}
		}
		return ignored;
	}
}
