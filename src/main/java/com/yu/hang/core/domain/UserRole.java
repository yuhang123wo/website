package com.yu.hang.core.domain;

import com.yu.hang.core.base.BaseModel;


/**
 * @类说明： 
 * 
 * @version 1.0
 * @创建时间：2017-6-30 15:23:59
 */
public class UserRole extends BaseModel {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1953478582927065466L;
	private int userId;
    private int roleId;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
}
