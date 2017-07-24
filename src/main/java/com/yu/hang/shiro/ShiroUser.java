package com.yu.hang.shiro;

public class ShiroUser {

	private long id;
	private String username;
	private String realname;
	private String img;
	private int status;

	public ShiroUser(long id, String username, String realname, String img, int status) {
		super();
		this.id = id;
		this.username = username;
		this.realname = realname;
		this.img = img;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
