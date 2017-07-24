package com.yu.hang.vo;

public class ValidateMsgVO {
	/**
	 * 字段名
	 */
	private String fieldName;
	/**
	 * 错误说明
	 */
	private String message;
	/**
	 * 错误值
	 */
	private Object rejectedValue;

	public ValidateMsgVO(String fieldName, String message, Object rejectedValue) {
		this.fieldName = fieldName;
		this.message = message;
		this.rejectedValue = rejectedValue;
	}

	public ValidateMsgVO() {

	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
