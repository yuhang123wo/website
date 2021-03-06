package com.yu.hang.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.yu.hang.core.base.BaseModel;

public class RoleVo extends BaseModel {
	private static final long serialVersionUID = -8059658265638542983L;

	@Length(max = 50, message = "名称长度不能超过50")
	@NotEmpty(message = "名称必填")
	private String name;
	@Length(max = 200, message = "备注长度不能超过200")
	private String remark;
	private Integer createId;
	@NotEmpty(message="权限必选")
	private String permission;
	
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

}
