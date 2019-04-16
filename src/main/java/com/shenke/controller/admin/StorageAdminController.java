package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.service.StorageService;

/**
 * 入库单Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/storage")
public class StorageAdminController {

	@Resource
	private StorageService storageService;

	/**
	 * 入库
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public Map<String, Object> add(Double weight, Integer saleListProductId, Integer jitaiProductionAllotId,
			Integer producionProcessId, Integer jitaiId) {
		storageService.add(weight, saleListProductId, jitaiProductionAllotId, producionProcessId, jitaiId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}
}
