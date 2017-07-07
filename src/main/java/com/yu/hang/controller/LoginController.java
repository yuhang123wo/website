package com.yu.hang.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping("index")
	public String index() {
		return "manage.index";
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login() {
		if (SecurityUtils.getSubject().getPrincipal() == null) {
			return "login";
		} else {
			return "redirect:/index";
		}
	}

	/**
	 * 登录失败
	 * 
	 * @return
	 */
	@RequestMapping("loginFail")
	public String loginFail(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,
			HttpServletRequest request, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, true);
		Object error = request
				.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		System.out.println(error+":"+userName);
		return "login";
	}
}
