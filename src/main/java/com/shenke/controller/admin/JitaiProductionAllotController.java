package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shenke.entity.JitaiProductionAllot;
import com.shenke.service.JitaiProductionAllotService;

/**
 * 机台生产分配Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/jitaiProductionAllot")
public class JitaiProductionAllotController {

	@Resource
	private JitaiProductionAllotService jitaiProductionAllotService;

	/**
	 * 查询所有生产通知单
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public List<JitaiProductionAllot> list() {
		return jitaiProductionAllotService.list();
	}

	/**
	 * 根据条件查询生产通知单信息
	 * 
	 * @return
	 */
	@RequestMapping("/screen")
	public Map<String, Object> screen(String allorTime, Integer jitai) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", jitaiProductionAllotService.screen(allorTime, jitai));
		map.put("success", true);
		return map;
	}
}
