package com.yu.hang.core.domain;

import java.util.Date;

import com.yu.hang.core.base.BaseModel;

/**
 * @类说明：
 * 
 * @version 1.0
 * @创建时间：2017-6-30 15:23:59
 */
public class Menu extends BaseModel {

	private static final long serialVersionUID = 1L;

	private int parentId;
	private String name;
	private String url;
	private String icon;
	private String remark;
	private int isShow;
	private int createId;
	private String permission;
	private int type;
	private Date updateTime;
	private Date createTime;

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getIsShow() {
		return this.isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public int getCreateId() {
		return this.createId;
	}

	public void setCreateId(int createId) {
		this.createId = createId;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
