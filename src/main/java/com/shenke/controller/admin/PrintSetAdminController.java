package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shenke.service.PrintSetService;

/**
 * 打印设置Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/printSet")
public class PrintSetAdminController {

	@Resource
	private PrintSetService printSetService;

	/**
	 * 打印设置
	 * 
	 * @return
	 */
	@RequestMapping("/selectRows")
	public Map<String, Object> printSet(HttpServletRequest request, HttpServletResponse response, String fields) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] fieldList = fields.split(",");
		request.getServletContext().setAttribute("fieldlist", fieldList);
		map.put("success", true);
		return map;
	}

	/**
	 * 获取设置的打印列
	 * 
	 * @return
	 */
	@RequestMapping("/getSelectRows")
	public Map<String, Object> getSelectRows(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("========================getSelectRows=============================");
		Map<String, Object> map = new HashMap<String, Object>();
		if (request.getServletContext().getAttribute("fieldlist") != null) {
			String[] fieldList = (String[]) request.getServletContext().getAttribute("fieldlist");
			for (int i = 0; i < fieldList.length; i++) {
				if (!fieldList[i].endsWith("Ipt")) {
					fieldList[i] += "Ipt";
				}
			}
			map.put("success", true);
			map.put("fields", fieldList);
		} else {
			map.put("success", false);
		}
		return map;
	}
}
