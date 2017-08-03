package com.yu.hang.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.hang.core.domain.Role;
import com.yu.hang.core.domain.Userinfo;
import com.yu.hang.core.service.RoleService;
import com.yu.hang.core.service.UserinfoService;
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
	@Resource
	private UserinfoService userinfoService;

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
	@ResponseBody
	public ResultMsg editRole(RoleVo role) {
		ValidateUtil.validate(role);
		Role v = CopyUtil.copyProperties(new Role(), role, new String[] { "name" });
		roleService.editRole(v, role.getPermission());
		return new ResultMsg().success();
	}

	/**
	 * 
	 * @return String
	 */
	@RequestMapping("role/editView/{id}")
	public String editIndex(Model model, @PathVariable("id") long id) {
		model.addAttribute("role", roleService.queryById(id));
		return "role.edit.view";
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 *             Object
	 */
	@RequestMapping("role/allMenu")
	@ResponseBody
	public Object allMenu(@RequestParam(defaultValue = "0", value = "roleId") int roleId)
			throws Exception {
		if (roleId == 0)
			return userinfoService.listAllMenu();
		return userinfoService.listMenuByRole(roleId);
	}

	/**
	 * 用户列表
	 * 
	 * @param request
	 * @param name
	 * @param model
	 * @param phone
	 * @return String
	 */
	@RequestMapping("user/list")
	public String userList(HttpServletRequest request, String username, Model model, String mobile) {
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usernameLike", StringHelper.addLike(username));
		map.put("mobile", StringHelper.trimString(mobile));
		Page<Userinfo> list = userinfoService.queryPageByParmas(map, pageNo, pageSize);
		model.addAttribute("userList", list);
		model.addAttribute("username", username);
		model.addAttribute("mobile", mobile);
		model.addAttribute("searchParams", "&username=" + StringHelper.getKString(username)
				+ "&mobile=" + StringHelper.getKString(mobile));
		return "user.index";
	}

	/**
	 * 新增用户
	 * 
	 * @param u
	 * @return Message
	 */
	@RequestMapping("user/add")
	public ResultMsg addUser(Userinfo u) {
		ValidateUtil.validate(u);
		userinfoService.addNewUser(u);
		return new ResultMsg().success();

	}

	/**
	 * 
	 * @param u
	 * @return ResultMsg
	 */
	@RequestMapping("user/addView")
	public String userAddView() {
		return "user.add.view";
	}

	/**
	 * 编辑用户
	 * 
	 * @param u
	 * @return Message
	 */
	@RequestMapping("user/edit")
	public Message editUser(Userinfo u) {
		return null;

	}

}
