package com.yu.hang.core.domain;

import java.util.Date;

import com.yu.hang.core.base.BaseModel;
/**
 * @类说明： 
 * 
 * @version 1.0
 * @创建时间：2017-8-7 14:55:26
 */
public class Dep extends BaseModel {

	private static final long serialVersionUID = 1L;
    
    private String name;
    private int parentId;
    private int state; // 0:禁用，1：启用
    private String remark;
    private Date createTime;
    private Date updateTime;
    private long userId;
    
    

    public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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
    
}
