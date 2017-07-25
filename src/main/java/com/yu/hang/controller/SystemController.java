package com.yu.hang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.hang.core.domain.Role;
import com.yu.hang.core.service.RoleService;
import com.yu.hang.core.validate.ValidateUtil;
import com.yu.hang.shiro.ShiroUser;
import com.yu.hang.util.ResultMsg;
import com.yu.hang.util.UserUtil;
import com.yu.hang.vo.RoleVo;

@Controller
@RequestMapping("sys")
public class SystemController {

	@Resource
	private RoleService roleService;

	/**
	 * 角色列表
	 * 
	 * @return
	 */
	@RequestMapping("role/list")
	public String roleList(Model model) {
		ShiroUser user = UserUtil.getUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createId", user.getId());
		List<Role> list = roleService.queryByParmas(map);
		model.addAttribute("roleList", list);
		return "role.index";
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("role/add")
	@ResponseBody
	public ResultMsg addRole(RoleVo role) {
		ValidateUtil.validate(role);
		
		return null;
	}

	/**
	 * 编辑角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("role/edit")
	public ResultMsg editRole(RoleVo role) {
		ValidateUtil.validate(role);
		return null;
	}
}
