package com.yu.hang.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.base.BaseServiceImpl;
import com.yu.hang.core.dao.RoleDao;
import com.yu.hang.core.domain.Role;
import com.yu.hang.core.domain.RoleMenu;
import com.yu.hang.core.exception.ValiAutoHandedException;
import com.yu.hang.core.service.RoleMenuService;
import com.yu.hang.core.service.RoleService;
import com.yu.hang.util.Constant;
import com.yu.hang.util.StringHelper;
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
	@Resource
	private RoleMenuService roleMenuService;

	public BaseDao<Role> getDao() {
		return roleDao;
	}

	@Override
	public void addNewRole(Role role, long createId, String permission) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", role.getName());
		map.put("createId", createId);
		int num = roleDao.countByParmas(map);
		if (num >= 1)
			throw new ValiAutoHandedException(Constant.VALIDATE_ERROR, new ValidateMsgVO("name",
					"该角色名已经被使用", role.getName()));
		role.setCreateId((int) createId);
		roleDao.save(role);
		// 添加权限
		this.addRoleMenu(permission, role.getId().intValue());
	}

	@Override
	public void editRole(Role role, String permission) {
		Role rv = roleDao.queryById(role.getId());
		if (rv == null)
			throw new ValiAutoHandedException(Constant.VALIDATE_ERROR, new ValidateMsgVO("name",
					"角色不存在", role.getName()));
		// 先删除原来关联
		roleMenuService.delByRoleId(rv.getId());
		// 再新增关联
		this.addRoleMenu(permission, rv.getId().intValue());
	}

	/**
	 * 
	 * @param permission
	 * @param roleId
	 *            void
	 */
	private void addRoleMenu(String permission, int roleId) {
		if (StringHelper.isNotNull(permission)) {
			String[] p = permission.split("\\,");
			List<RoleMenu> list = new ArrayList<RoleMenu>();
			for (String ps : p) {
				RoleMenu m = new RoleMenu();
				m.setMenuId(Integer.parseInt(ps));
				m.setRoleId(roleId);
			}
			roleMenuService.saveBatch(list);
		}
	}
}