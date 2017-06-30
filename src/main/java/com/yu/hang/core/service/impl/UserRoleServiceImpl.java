package com.yu.hang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.UserRoleDao;
import com.yu.hang.core.domain.UserRole;
import com.yu.hang.core.service.UserRoleService;

/**
 * @类说明：
 * 
 * @创建时间：2017-6-30 15:23:59
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {
        
	@Resource
	private UserRoleDao userRoleDao;
	
	public BaseDao<UserRole> getDao() {
		return userRoleDao;
	}
}