package com.yu.hang.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.RoleDao;
import com.yu.hang.core.domain.Role;
import com.yu.hang.core.exception.ValiAutoHandedException;
import com.yu.hang.core.service.RoleService;
import com.yu.hang.util.Constant;
import com.yu.hang.vo.ValidateMsgVO;

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
	public void addNewRole(Role role, long createId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", role.getName());
		map.put("createId", createId);
		int num = roleDao.countByParmas(map);
		if (num >= 1)
			throw new ValiAutoHandedException(Constant.VALIDATE_ERROR, new ValidateMsgVO("name",
					"该角色名已经被使用", role.getName()));
		role.setCreateId((int) createId);
		roleDao.save(role);
	}
}