package com.yu.hang.core.domain;

import java.util.Date;

import com.yu.hang.core.base.BaseModel;

public class Flow extends BaseModel {

	private static final long serialVersionUID = 1286353258954594118L;

	private String name;

	private String source;
	
	private String sourceImg;

	private int state;

	private String remark;

	private Date createTime;

	private Date updateTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSourceImg() {
		return sourceImg;
	}

	public void setSourceImg(String sourceImg) {
		this.sourceImg = sourceImg;
	}

}
