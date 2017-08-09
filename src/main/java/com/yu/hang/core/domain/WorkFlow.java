package com.yu.hang.core.domain;

import java.util.Date;

import com.yu.hang.core.base.BaseModel;

/**
 * @类说明：
 * 
 * @version 1.0
 * @创建时间：2017-8-8 17:32:50
 */
public class WorkFlow extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String name;
	private String source;
	private String sourceImg;
	private String wkey;
	private int state;
	private String remark;
	private Date createTime;
	private Date updateTime;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceImg() {
		return this.sourceImg;
	}

	public void setSourceImg(String sourceImg) {
		this.sourceImg = sourceImg;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getWkey() {
		return wkey;
	}

	public void setWkey(String wkey) {
		this.wkey = wkey;
	}



}
