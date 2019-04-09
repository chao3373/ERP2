package com.shenke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/login.html";
	}

	@RequestMapping("/import")
	public String port() {
		return "redirect:/indexx.html";
	}
}