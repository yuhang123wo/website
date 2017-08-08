package com.yu.hang.shiro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import com.yu.hang.core.domain.Dep;
import com.yu.hang.core.domain.DepSerializable;
import com.yu.hang.core.domain.Userinfo;
import com.yu.hang.core.service.DepService;
import com.yu.hang.core.service.UserinfoService;
import com.yu.hang.exception.UserNotException;
import com.yu.hang.util.Constant;
import com.yu.hang.util.redis.JedisTemplate;

public class ShiroDbRealm extends AuthorizingRealm {

	@Resource
	private UserinfoService userinfoService;
	@Resource
	private DepService depService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private JedisTemplate jedisTemplate;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
		String captcha = token.getCaptcha();
		// TODO test
		// String exitCode = (String) SecurityUtils.getSubject().getSession()
		// .getAttribute("captchaTxt");
		// if (StringUtils.isEmpty(captcha) || StringUtils.isEmpty(exitCode)
		// || !captcha.equalsIgnoreCase(exitCode)) {
		// throw new CaptchaException();
		// }
		// Userinfo u = userinfoService.queryByName(token.getUsername());
		Userinfo u = userinfoService.queryByName("1");
		if (u == null) {
			throw new UserNotException();
		}
		try {
			AuthenticationInfo info = new SimpleAuthenticationInfo(new ShiroUser(u.getId(),
					u.getUsername(), u.getRealname(), u.getImg(), u.getStatus()), u.getPassword(),
					getName());
			initDep(u.getId());
			return info;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 初始化部门 void
	 */
	private void initDep(long userId) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("state", Constant.STATE_T);
			List<Dep> list = depService.queryByParmas(map);
			if (CollectionUtils.isEmpty(list))
				return;
			DepSerializable dep = new DepSerializable(list);
			jedisTemplate.setObj(Constant.DEP_REDIS_KEY + userId, dep, Constant.DEP_REDIS_TIME);
		} catch (Exception e) {
			logger.error("初始化部门信息异常", e);
		}
	}
}
