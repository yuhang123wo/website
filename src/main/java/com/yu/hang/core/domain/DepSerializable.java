package com.yu.hang.core.domain;

import java.util.List;

public class DepSerializable implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6246818918704439023L;

	private List<Dep> list;

	
	
	public DepSerializable(List<Dep> list) {
		super();
		this.list = list;
	}

	public List<Dep> getList() {
		return list;
	}

	public void setList(List<Dep> list) {
		this.list = list;
	}

}
