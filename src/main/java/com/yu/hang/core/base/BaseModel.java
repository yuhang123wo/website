package com.yu.hang.core.base;

/**
 * 基础数据模型
 * 
 * @author yuhang
 * @Date 2017年6月8日
 * @desc
 */
public class BaseModel {
	/**
	 * 主键
	 */
	private Long id = 0L;

	/**
	 * 获取主键
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
