package com.yu.hang.core.domain;

import com.yu.hang.core.base.BaseModel;


/**
 * @类说明： 
 * 
 * @version 1.0
 * @创建时间：2017-6-30 15:23:59
 */
public class RoleMenu extends BaseModel {


    /**
	 * 
	 */
	private static final long serialVersionUID = 270500829206318755L;
	private int roleId;
    private int menuId;

    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    public int getMenuId() {
        return this.menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    
}
