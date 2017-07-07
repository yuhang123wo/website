package com.yu.hang.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * 
 * @author yuhang
 * @Date 2017年6月30日
 * @desc
 */
public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	private String loginPrefix;
	private String fail;

	protected boolean executeLogin(ServletRequest request, ServletResponse response)
			throws Exception {
		AuthenticationToken token = createToken(request, response);
		if (token == null) {
			throw new IllegalStateException("create AuthenticationToken error");
		}
		try {
			Subject subject = getSubject(request, response);
			subject.login(token);
			return super.onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, getFail(), e, request, response);
		}
	}

	protected AuthenticationToken createToken(

	ServletRequest request, ServletResponse response) {

		String username = getUsername(request);

		String password = getPassword(request);

		String captcha = getCaptcha(request);

		return new UsernamePasswordCaptchaToken(username, password.toCharArray(), captcha);

	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, DEFAULT_CAPTCHA_PARAM);

	}

	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response)
			throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String successUrl = req.getParameter("");
		if (StringUtils.isBlank(successUrl)) {
			if (req.getRequestURI().startsWith(req.getContextPath() + "")) {
				// 后台直接返回首页
				successUrl = getLoginPrefix();
				// 清除SavedRequest
				WebUtils.getAndClearSavedRequest(request);
				WebUtils.issueRedirect(request, response, successUrl, null, true);
				return;
			} else {
				successUrl = getSuccessUrl();
			}
		}
		WebUtils.redirectToSavedRequest(req, res, successUrl);
	}

	/**
	 * 登录失败
	 */
	private boolean onLoginFailure(AuthenticationToken token, String failureUrl,
			AuthenticationException e, ServletRequest request, ServletResponse response) {
		String className = e.getClass().getName();
		request.setAttribute(getFailureKeyAttribute(), className);
		if (failureUrl != null || StringUtils.isNotBlank(failureUrl)) {
			try {
				request.getRequestDispatcher(failureUrl).forward(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return true;
	}

	public String getLoginPrefix() {
		return loginPrefix;
	}

	public void setLoginPrefix(String loginPrefix) {
		this.loginPrefix = loginPrefix;
	}

	public String getFail() {
		return fail;
	}

	public void setFail(String fail) {
		this.fail = fail;
	}

}
