package com.yu.hang.core.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.go2.shipping.common.mapper.JsonMapper;
import com.yu.hang.core.exception.ValiAutoHandedException;
import com.yu.hang.core.exception.ValidatorSingleFactory;
import com.yu.hang.util.Constant;
import com.yu.hang.util.StringHelper;
import com.yu.hang.vo.ValidateMsgVO;

/**
 * 
 * @author yuhang
 * @Date 2017年7月24日
 * @desc
 */
public class ValidateUtil {
	private static Logger logger = LoggerFactory.getLogger(ValidateUtil.class);

	private static JsonMapper mapper = JsonMapper.nonEmptyMapper();

	public static <T> void validate(T requestObject) {

		logger.debug("请求参数：" + mapper.toJson(requestObject));

		// 先验证是否为空
		if (!StringHelper.objectIsNotNull(requestObject)) {
			throw new ValiAutoHandedException(Constant.VALIDATE_ERROR, new ValidateMsgVO(
					"requestObject", "不能为空", null));
		}

		Validator validator = ValidatorSingleFactory.getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(requestObject);
		if (!violations.isEmpty()) {
			throw new ValiAutoHandedException(Constant.VALIDATE_ERROR, violations);
		}
	}
}
