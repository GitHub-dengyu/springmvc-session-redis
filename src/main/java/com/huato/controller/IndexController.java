package com.huato.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
import com.huato.bean.User;

@Controller
@SessionAttributes("user")
public class IndexController {

	@RequestMapping("hello.do")
	@ResponseBody
	public String hello() {
		return "hello";
	}

	@RequestMapping(value = "/login.do")
	public String login(Model model, String username) {
		// 存储的是json对象 这样数据在session中
		model.addAttribute("user", JSON.toJSON((new User(username, "123456"))));
		return "login";
	}

	@RequestMapping(value = "/")
	public String index(@ModelAttribute("user") User user) {
		return "index";
	}
}
