package com.yu.hang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.hang.exception.CaptchaException;
import com.yu.hang.exception.UserNotException;

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
		String error = request.getAttribute(
				FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME).toString();
		String msg = "验证失败";
		if (error.equals(IncorrectCredentialsException.class.getName())) {
			msg = "用户名或密码错误";
		}
		if (error.equals(CaptchaException.class.getName())) {
			msg = "验证码错误";
		}
		if (error.equals(UserNotException.class.getName())) {
			msg = "用户不存在";
		}
		model.addAttribute("msg", msg);
		return "login";
	}

	@RequestMapping("/captcha")
	@ResponseBody
	public Object identifyImg(HttpServletRequest request, HttpServletResponse response) {
		String code = RandomStringUtils.randomNumeric(4);
		// session 传值
		request.getSession().setAttribute("captchaTxt", code);
		return code;
	}
}
