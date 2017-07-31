package com.yu.hang.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.RoleMenuDao;
import com.yu.hang.core.domain.RoleMenu;
import com.yu.hang.core.service.RoleMenuService;

/**
 * @类说明：
 * 
 * @创建时间：2017-6-30 15:23:59
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu> implements RoleMenuService {
        
	@Resource
	private RoleMenuDao roleMenuDao;
	
	public BaseDao<RoleMenu> getDao() {
		return roleMenuDao;
	}

	@Override
	public void delByRoleId(long roleId) {
		roleMenuDao.delByRoleId(roleId);
	}

	@Override
	public List<Long> findMenuByRole(long roleId) {
		return roleMenuDao.findMenuByRole(roleId);
	}
}