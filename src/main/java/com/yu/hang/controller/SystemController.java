package com.yu.hang.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.hang.core.domain.Role;
import com.yu.hang.core.service.RoleService;
import com.yu.hang.core.validate.ValidateUtil;
import com.yu.hang.shiro.ShiroUser;
import com.yu.hang.util.CopyUtil;
import com.yu.hang.util.ResultMsg;
import com.yu.hang.util.StringHelper;
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
	public String roleList(Model model, HttpServletRequest request, String name) {
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
		ShiroUser user = UserUtil.getUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createId", user.getId());
		map.put("nameLike", StringHelper.addLike(name));
		Page<Role> list = roleService.queryPageByParmas(map, pageNo, pageSize);
		model.addAttribute("roleList", list);
		model.addAttribute("name", name);
		model.addAttribute("searchParams", "&name=" + StringHelper.getKString(name));
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
		roleService.addNewRole(CopyUtil.copyProperties(new Role(), role), UserUtil.getUser()
				.getId(), role.getPermission());
		return new ResultMsg().success();
	}

	/**
	 * 角色添加界面
	 * 
	 * @return String
	 */
	@RequestMapping("role/addView")
	public String roleIndex() {
		return "role.add.view";
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
		Role v = CopyUtil.copyProperties(new Role(), role, new String[] { "name" });
		roleService.editRole(v, role.getPermission());
		return new ResultMsg().success();
	}

	public static void main(String[] args) {
		System.out.println(Math.floor(2 / 2) + ":" + (2 / 0.8D));
	}
}
