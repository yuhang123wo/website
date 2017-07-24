package com.yu.hang.core.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.yu.hang.vo.ValidateMsgVO;

/**
 * 
 * @author yuhang
 * @Date 2017年7月24日
 * @desc
 */
public class ValiAutoHandedException extends DefaultAutoHandledException {

	private static final long serialVersionUID = -7264433637671418517L;
	private String description;

	public <T> ValiAutoHandedException(String description, Set<ConstraintViolation<T>> violations) {
		this.description = description;
		// 错误信息封装
		List<ValidateMsgVO> msgs = new ArrayList<ValidateMsgVO>();
		for (ConstraintViolation<T> violation : violations) {
			msgs.add(new ValidateMsgVO(violation.getPropertyPath().toString(), violation
					.getMessage(), violation.getInvalidValue()));
		}
		this.responseObject = msgs;
	}

	public ValiAutoHandedException(String description, ValidateMsgVO validateMsgVO) {
		this.description = description;
		// 错误信息封装
		List<ValidateMsgVO> msgs = new ArrayList<ValidateMsgVO>();
		msgs.add(validateMsgVO);
		this.responseObject = msgs;
	}

	public String getDescription() {
		return description;
	}
}
