package com.yu.hang.util;

import org.apache.shiro.SecurityUtils;

import com.yu.hang.shiro.ShiroUser;

public class UserUtil {

	public static ShiroUser getUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
}
