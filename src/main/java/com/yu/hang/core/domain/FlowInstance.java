package com.yu.hang.core.domain;

import java.util.Date;

import com.yu.hang.core.base.BaseModel;

/**
 * @类说明：
 * 
 * @version 1.0
 * @创建时间：2017-8-8 17:32:50
 */
public class FlowInstance extends BaseModel {

	private static final long serialVersionUID = 1L;

	private int flowId;
	private String title;
	private long userId;
	private Date createTime;
	private Date updateTime;
	private int flowState;
	private String instanceId;

	public int getFlowId() {
		return this.flowId;
	}

	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public int getFlowState() {
		return this.flowState;
	}

	public void setFlowState(int flowState) {
		this.flowState = flowState;
	}

	public String getInstanceId() {
		return this.instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

}
