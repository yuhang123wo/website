package com.yu.hang.core.exception;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorSingleFactory {
	private static ValidatorFactory factory;

	static {
		factory = Validation.buildDefaultValidatorFactory();
	}

	public static Validator getValidator() {
		return factory.getValidator();
	}
}
