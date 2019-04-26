package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 打印Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/print")
public class PrintAdminController {

	/**
	 * 添加选中的属性到application
	 * 
	 * @return
	 */
	@RequestMapping("/printSet")
	public Map<String, Object> printSet(HttpServletRequest request, HttpServletResponse response, Boolean biaoqian,
			Boolean changdu, Boolean houdu, Integer biaoqianshu, Integer baozhuangshu, Integer banzu, Integer staff, Boolean zhongliang) {
		request.getServletContext().setAttribute("biaoqian", biaoqian);
		request.getServletContext().setAttribute("changdu", changdu);
		request.getServletContext().setAttribute("houdu", houdu);
		request.getServletContext().setAttribute("biaoqianshu", biaoqianshu);
		request.getServletContext().setAttribute("baozhuangshu", baozhuangshu);
		request.getServletContext().setAttribute("banzu", banzu);
		request.getServletContext().setAttribute("staff", staff);
		request.getServletContext().setAttribute("zhongliang", zhongliang);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}

	/**
	 * 获取之前设置的打印属性
	 * 
	 * @return
	 */
	@RequestMapping("/getPrintSet")
	public Map<String, Object> getPrintSet(HttpServletResponse response, HttpServletRequest request) {
		Boolean biaoqian = (Boolean) request.getServletContext().getAttribute("biaoqian");
		Boolean changdu = (Boolean) request.getServletContext().getAttribute("changdu");
		Boolean houdu = (Boolean) request.getServletContext().getAttribute("houdu");
		Boolean zhongliang = (Boolean) request.getServletContext().getAttribute("zhongliang");
		Integer biaoqianshu = (Integer) request.getServletContext().getAttribute("biaoqianshu");
		Integer baozhuangshu = (Integer) request.getServletContext().getAttribute("baozhuangshu");
		Integer banzu = (Integer) request.getServletContext().getAttribute("banzu");
		Integer staff = (Integer) request.getServletContext().getAttribute("staff");

		if (baozhuangshu == null) {
			baozhuangshu = 0;
		}
		if (biaoqianshu == null) {
			biaoqianshu = 0;
		}
		if (biaoqian == null) {
			biaoqian = false;
		}
		if (changdu == null) {
			changdu = false;
		}
		if (houdu == null) {
			houdu = false;
		}
		if (zhongliang == null) {
			zhongliang = false;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("biaoqian", biaoqian);
		map.put("changdu", changdu);
		map.put("houdu", houdu);
		map.put("biaoqianshu", biaoqianshu);
		map.put("baozhuangshu", baozhuangshu);
		map.put("banzu", banzu);
		map.put("staff", staff);
		map.put("zhongliang", zhongliang);
		return map;
	}

}
