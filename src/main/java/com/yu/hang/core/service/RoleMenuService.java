package com.yu.hang.core.service;

import java.util.List;

import com.yu.hang.core.base.BaseService;
import com.yu.hang.core.domain.RoleMenu;

/**
 * @类说明：
 * 
 * @创建时间：2017-6-30 15:23:59
 */
public interface RoleMenuService extends BaseService<RoleMenu> {
	/**
	 * 按角色删除
	 * 
	 * @param roleId
	 *            void
	 */
	void delByRoleId(long roleId);

	/**
	 * 
	 * @param roleId
	 * @return List<Long>
	 */
	List<Long> findMenuByRole(long roleId);

}