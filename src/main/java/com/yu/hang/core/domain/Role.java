package com.yu.hang.core.domain;

import java.util.Date;

import com.yu.hang.core.base.BaseModel;
/**
 * @类说明： 
 * 
 * @version 1.0
 * @创建时间：2017-6-30 15:23:59
 */
public class Role extends BaseModel {

	private static final long serialVersionUID = 1L;
    
    private String name;
    private String remark;
    private int createId;
    private Date updateTime;
    private Date createTime;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public int getCreateId() {
        return this.createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
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
    
    
    public static void main(String[] args) {
    	 System.out.println(Math.max(1, 1 - 100/2));
	}
}
