package com.yu.hang.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import com.yu.hang.core.domain.Userinfo;
import com.yu.hang.core.service.UserinfoService;
import com.yu.hang.exception.CaptchaException;
import com.yu.hang.exception.UserNotException;

public class ShiroDbRealm extends AuthorizingRealm {

	@Resource
	private UserinfoService userinfoService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
		String captcha = token.getCaptcha();
		//TODO test
//		String exitCode = (String) SecurityUtils.getSubject().getSession()
//				.getAttribute("captchaTxt");
//		if (StringUtils.isEmpty(captcha) || StringUtils.isEmpty(exitCode)
//				|| !captcha.equalsIgnoreCase(exitCode)) {
//			throw new CaptchaException();
//		}
//		Userinfo u = userinfoService.queryByName(token.getUsername());
		Userinfo u = userinfoService.queryByName("1");
		if (u == null) {
			throw new UserNotException();
		}
		try {
			AuthenticationInfo info = new SimpleAuthenticationInfo(new ShiroUser(u.getId(),
					u.getUsername(), u.getRealname(), u.getImg(), u.getStatus()), u.getPassword(),
					getName());
			return info;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
