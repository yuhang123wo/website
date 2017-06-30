package com.yu.hang.core.domain;

import java.util.Date;

import com.yu.hang.core.base.BaseModel;
/**
 * @类说明： 
 * 
 * @version 1.0
 * @创建时间：2017-6-30 15:23:59
 */
public class Userinfo extends BaseModel {

	private static final long serialVersionUID = 1L;
    
    private String username; // 用户名
    private String password; // 密码
    private String realname; // 真实名
    private String img; // 头像
    private String mobile; // 电话
    private String email; // 邮箱
    private int sex; // 性别
    private String address; // 地址
    private String birthday; // 生日
    private int status; // 状态
    private int type; // 类型
    private int deptId;
    private Date updateTime;
    private Date createTime;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
    
    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
    
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public int getDeptId() {
        return this.deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
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
