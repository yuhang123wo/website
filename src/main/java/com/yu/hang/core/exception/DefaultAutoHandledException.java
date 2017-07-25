package com.yu.hang.core.exception;

/**
 * 自定义异常基类
 * 
 * @author yuhang
 * @Date 2017年7月24日
 * @desc
 */
public class DefaultAutoHandledException extends RuntimeException {

	private static final long serialVersionUID = -8050743631118122307L;

	protected String description;
	protected String[] params;
	protected Object responseObject;

	public DefaultAutoHandledException() {
		super();
	}

	public DefaultAutoHandledException(Throwable t) {
		super(t);
	}

	public DefaultAutoHandledException(String description) {
		super(description);
		this.description = description;
	}

	public DefaultAutoHandledException(String description, Object responseObject) {
		super(description);
		this.description = description;
		this.responseObject = responseObject;
	}

	public DefaultAutoHandledException(String description, Throwable t) {
		super(t);
		this.description = description;
	}

	public DefaultAutoHandledException(String description, Object responseObject, Throwable t) {
		super(t);
		this.description = description;
		this.responseObject = responseObject;
	}

	public DefaultAutoHandledException(String description, String... params) {
		super();
		this.description = description;
		this.params = params;
	}

	public DefaultAutoHandledException(String description, Object responseObject, String... params) {
		super();
		this.description = description;
		this.responseObject = responseObject;
		this.params = params;
	}

	public DefaultAutoHandledException(String description, Throwable t, String... params) {
		super(t);
		this.description = description;
		this.params = params;
	}

	public DefaultAutoHandledException(String description, Object responseObject, Throwable t,
			String... params) {
		super();
		this.description = description;
		this.responseObject = responseObject;
		this.params = params;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}

}
