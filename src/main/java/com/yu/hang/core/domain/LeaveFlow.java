package com.yu.hang.core.domain;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import com.yu.hang.core.base.BaseModel;

/**
 * @类说明：
 * 
 * @version 1.0
 * @创建时间：2017-8-9 14:27:19
 */
public class LeaveFlow extends BaseModel {

	private static final long serialVersionUID = 4905884108823808770L;
	@NotEmpty(message = "标题不能为空")
	private String title;
	@NotEmpty(message="请假事由不能为空")
	private String content;
	private long userId;
	private String username;
	private int dayCount;
	@NotEmpty(message = "开始时间不能为空")
	private String startTime;
	@NotEmpty(message = "结束时间不能为空")
	private String endTime;
	private Date createTime;
	private Date updateTime;
	private int state;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDayCount() {
		return this.dayCount;
	}

	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
