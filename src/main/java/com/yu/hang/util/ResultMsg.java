package com.yu.hang.util;

public class ResultMsg {

	private int code;// 状态代码
	private String msg;// 信息
	private Object data;
	public static int SUCCESS = 0;
	public static int FAIL = -1;

	public ResultMsg() {
		super();
	}

	public ResultMsg(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResultMsg(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResultMsg success() {
		this.code = SUCCESS;
		return this;
	}

	public ResultMsg successObj(Object data) {
		this.code = SUCCESS;
		this.data = data;
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
