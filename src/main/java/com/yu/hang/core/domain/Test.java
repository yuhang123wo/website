package com.yu.hang.core.domain;

import com.yu.hang.core.base.BaseModel;
import com.yu.hang.util.poi.ModelProp;
import com.yu.hang.util.poi.ModelTitle;

@ModelTitle(name = "excel测试")
public class Test extends BaseModel {
	private static final long serialVersionUID = 7973622664955258182L;
	@ModelProp(name = "电话", colIndex = 1, nullable = false)
	private String name;
	@ModelProp(name = "电话2", colIndex = 0, nullable = false)
	private String s;

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
