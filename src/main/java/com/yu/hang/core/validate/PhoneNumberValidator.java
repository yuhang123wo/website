package com.yu.hang.core.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yu.hang.annotation.PhoneNumberAnnotation;
import com.yu.hang.util.Constant;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberAnnotation, String> {

	@Override
	public void initialize(PhoneNumberAnnotation constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {
			// context.disableDefaultConstraintViolation();// 禁用默认的message的值
			// // 重新添加错误提示语句
			// context.buildConstraintViolationWithTemplate("角色名称不能重复").addConstraintViolation();
			if (value.matches(Constant.PHONE_REG))
				return true;
		}
		return false;

	}
}
