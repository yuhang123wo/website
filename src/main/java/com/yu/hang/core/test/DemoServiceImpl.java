package com.yu.hang.core.test;

import java.util.ArrayList;
import java.util.List;

import com.yu.hang.core.domain.Userinfo;

public class DemoServiceImpl implements DemoService {

	@Override
	public String getHell(String name) {
		return "Hello " + name;
	}

	@Override
	public List<?> getUsers() {
		List<Userinfo> list = new ArrayList<Userinfo>();
		Userinfo u = new Userinfo();
		u.setUsername("ret");
		list.add(u);

		Userinfo u1 = new Userinfo();
		u1.setUsername("xyz");
		list.add(u1);

		return list;
	}

}
