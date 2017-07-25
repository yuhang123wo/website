package com.yu.hang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.RoleDao;
import com.yu.hang.core.domain.Role;
import com.yu.hang.core.service.RoleService;

/**
 * @类说明：
 * 
 * @创建时间：2017-6-30 15:23:59
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
        
	@Resource
	private RoleDao roleDao;
	
	public BaseDao<Role> getDao() {
		return roleDao;
	}

	@Override
	public void addOrUpdateRole(Role role) {
	}
}