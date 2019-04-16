package com.shenke.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.shenke.entity.Storage;
import com.shenke.service.StorageService;
import com.shenke.service.UserService;

@Controller
public class IndexController {

	@Resource
	private UserService userService;
	
	@Resource
	private StorageService storageService;
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/login.html";
	}

	@RequestMapping("/import")
	public String port() {
		return "redirect:/indexx.html";
	}

	@ResponseBody
	@RequestMapping("/static/denglu")
	public Map<String, Object> denglu(String name, String psw) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userService.findNamePsw(name, psw) == null) {
			map.put("errorInfo", "用户名或密码错误");

			return map;
		}
		List<Storage> list = storageService.fandAll();
		map.put("rows", list);
		return map;
	}

	@ResponseBody
	@RequestMapping("/static/shangchuan")
	public String shangchuan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader br = request.getReader();
		String str, wholeStr = "";
		while ((str = br.readLine()) != null) {
			wholeStr += str;
		}
		System.out.println(wholeStr);
		return "上传成功";
	}

	@ResponseBody
	@RequestMapping("/static/ceshi")
	public String ceshi(String name, String psw) {
		if (userService.findNamePsw(name, psw) == null) {
			return "用户名或密码错误";
		}
		return "连接成功";
	}
}