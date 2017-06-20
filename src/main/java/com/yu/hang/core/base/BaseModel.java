package com.yu.hang.core.base;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
	
	
	@Override
    public boolean equals(Object o) {
        return o != null && o.getClass().equals(this.getClass()) && EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}