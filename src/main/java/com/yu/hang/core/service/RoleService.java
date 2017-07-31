package com.yu.hang.core.service;

import com.yu.hang.core.base.BaseService;
import com.yu.hang.core.domain.Role;

/**
 * @类说明：
 * 
 * @创建时间：2017-6-30 15:23:59
 */
public interface RoleService extends BaseService<Role> {

	/**
	 * 新增角色
	 * @param role
	 * @param createId
	 * 下午3:46:24
	 * void
	 */
	void addNewRole(Role role, long createId,String permission);
	/**
	 * 编辑角色
	 * @param role
	 * @param permission
	 * void
	 */
	void editRole(Role role,String permission);

}