package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shenke.entity.Log;
import com.shenke.service.JiTaiService;
import com.shenke.service.LogService;
import com.shenke.service.SaleListProductService;

/**
 * 生产订单Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/production")
public class ProductionAdminController {

	@Resource
	private JiTaiService jiTaiService;

	@Resource
	private SaleListProductService SaleListProductService;
	
	@Resource
	private LogService logService;

	/**
	 * 分配机台信息
	 * 
	 * @return
	 */
	@RequestMapping("/jitaiAllocation")
	public Map<String, Object> jitaiAllocation(String sale, Integer jitai) {
		Map<String, Object> map = new HashMap<String, Object>();
		String jitaiName = jiTaiService.findById(jitai).getName();
		String[] ids = sale.split(",");
		
		logService.save(new Log(Log.UPDATE_ACTION, "分配机台"));
		
		for (int i = 0; i < ids.length; i++) {
			SaleListProductService.auditFailure(Integer.parseInt(ids[i]), "分配机台："+jitaiName);
		}
		
		map.put("success", true);
		return map;
	}
}
