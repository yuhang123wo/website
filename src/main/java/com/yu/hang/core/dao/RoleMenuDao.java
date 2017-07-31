package com.yu.hang.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import com.yu.hang.core.base.BaseDao;
import com.yu.hang.core.domain.RoleMenu;

/**
 * @类说明：
 * 
 * @version 1.0
 * @创建时间：2017-6-30 15:23:59
 */
public interface RoleMenuDao extends BaseDao<RoleMenu> {

	@Delete("delete from role_menu where role_id=#{roleId}")
	void delByRoleId(@RequestParam("roleId") long roleId);

	@Select("select menu_id from role_menu where role_id=#{roleId}")
	List<Long> findMenuByRole(@RequestParam("roleId") long roleId);
}