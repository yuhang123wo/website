package com.yu.hang.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yu.hang.core.domain.Test;
import com.yu.hang.core.domain.note.TestNode;
import com.yu.hang.core.service.TestService;

@Controller
public class TestController {

	@Resource
	private TestService testService;

	@RequestMapping("testN")
	public void testN() {
		Test t = new Test();
		t.setName("g");
		testService.save(t);
		TestNode note = testService.queryNode();
		System.out.println(note.toString());
	}
}
