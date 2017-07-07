package com.yu.hang.shiro;

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
import com.yu.hang.exception.CaptchaException;
import com.yu.hang.util.MD5;

public class ShiroDbRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
		String captcha = token.getCaptcha();
		String exitCode = (String) SecurityUtils.getSubject().getSession()
				.getAttribute("captchaTxt");
		if (StringUtils.isEmpty(captcha) || StringUtils.isEmpty(exitCode)
				|| !captcha.equalsIgnoreCase(exitCode)) {
			throw new CaptchaException();
		}
		try {
			Userinfo u = new Userinfo();
			u.setUsername("1");
			u.setPassword(MD5.md5Encode("1"));
			AuthenticationInfo info = new SimpleAuthenticationInfo(u, u.getPassword(), getName());
			return info;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
