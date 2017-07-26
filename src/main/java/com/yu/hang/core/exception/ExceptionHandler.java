package com.yu.hang.core.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.yu.hang.common.mapper.JsonMapper;
import com.yu.hang.util.Constant;
import com.yu.hang.util.ResultMsg;

/**
 * 统计异常处理
 * 
 * @author yuhang
 * @Date 2017年7月24日
 * @desc
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private JsonMapper mapper = JsonMapper.nonEmptyMapper();

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		Map<String, Object> attributes = new HashMap<String, Object>();
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		DefaultAutoHandledException defaultAutoHandledException = null;

		// 如果是自定义异常，则直接转换，否则作为统一的未知异常
		if (ex instanceof DefaultAutoHandledException) {
			defaultAutoHandledException = (DefaultAutoHandledException) ex;
		} else {
			defaultAutoHandledException = new DefaultAutoHandledException(
					Constant.UNKNOWN_EXCEPTION, ex);
		}
		// log用信息直接输出到log
		if (defaultAutoHandledException.getResponseObject() != null) {
			logger.error("[HandlerExceptionResolver] responseError: "
					+ mapper.toJson(defaultAutoHandledException.getResponseObject()));
		}
		attributes.put("code", ResultMsg.FAIL);
		attributes.put("msg", defaultAutoHandledException.getDescription());
		attributes.put("data", defaultAutoHandledException.getResponseObject());
		// 根据接口返回类型返回view或JSON
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(handlerMethod.getMethod(),
				ResponseBody.class);
		if (responseBodyAnn == null) {
			ModelAndView mav = new ModelAndView("/error");
			mav.addAllObjects(attributes);
			return mav;
		} else {
			view.setAttributesMap(attributes);
			ModelAndView mav = new ModelAndView();
			mav.setView(view);
			return mav;
		}

	}
}
