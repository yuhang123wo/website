package com.yu.hang.core.service;

import java.util.List;

import com.yu.hang.core.base.BaseService;
import com.yu.hang.core.domain.Userinfo;
import com.yu.hang.core.domain.note.TreeNode;

/**
 * @类说明：
 * 
 * @创建时间：2017-6-30 15:23:59
 */
public interface UserinfoService extends BaseService<Userinfo> {

	/**
	 * 取所有菜单
	 * 
	 * @return
	 */
	List<TreeNode> listAllMenu();

	/**
	 * 根据用户名密码查询
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 */
	Userinfo queryByName(String username);
}