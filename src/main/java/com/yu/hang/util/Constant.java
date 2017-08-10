package com.yu.hang.util;

public class Constant {

	public static String PWD_PREFIX = "pwd_yh";
	/**
	 * 验证错误
	 */
	public static final String VALIDATE_ERROR = "验证错误";
	/**
	 * 未知异常
	 */
	public static final String UNKNOWN_EXCEPTION = "未知异常";
	/**
	 * 手机号正则表达式
	 */
	public static final String PHONE_REG = "^(0|86|17951)?(13[0-9]|15[012356789]|17[0135678]|18[0-9]|14[57])[0-9]{8}$";

	/**
	 * 状态可用
	 */
	public static final int STATE_T = 1;
	/**
	 * 状态不可用
	 */
	public static final int STATE_F = 0;

	/**
	 * 部门redis
	 */
	public static final String DEP_REDIS_KEY = "dep_redis_key_";
	/**
	 * DEP_REDIS_TIME
	 */
	public static final int DEP_REDIS_TIME = 60 * 20;

	/**
	 * 流程文件存入目录
	 */
	public static final String DEPLOY_PATH = "deployments/";
	/**
	 * 部门主管
	 */
	public static final int DEPT_GRADE_M = 1;
	/**
	 * 部门普通员工
	 */
	public static final int DEPT_GRADE_P = 0;
	/**
	 * 临时员工
	 */
	public static final int DEPT_GRADE_L = -1;

}
