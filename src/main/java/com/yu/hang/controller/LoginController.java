package com.yu.hang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("index")
	public String index(){
		return "manage.index";
	}
	
	@RequestMapping("login")
	public String newFile(){
		return "login";
	}
}
