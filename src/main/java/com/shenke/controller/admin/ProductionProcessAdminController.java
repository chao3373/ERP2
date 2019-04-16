package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shenke.entity.Log;
import com.shenke.entity.ProductionProcess;
import com.shenke.service.LogService;
import com.shenke.service.ProductionProcessService;
import com.shenke.util.StringUtil;

/**
 * 生产加工单Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/productionProcess")
public class ProductionProcessAdminController {

	@Resource
	private ProductionProcessService productionProcessService;

	@Resource
	private LogService logService;

	/**
	 * 根据机台查询所有生产加工单
	 * 
	 * @return
	 */
	@RequestMapping("/selectByJitaiIdAndIssueStateAndInformNumber")
	public Map<String, Object> selectByJitaiIdAndIssueStateAndInformNumber(Integer jitaiId, String state,
			String informNumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(state)) {
			state = "%" + state + "%";
		} else {
			state = "%";
		}
		Long infLong = null;
		if (StringUtil.isNotEmpty(informNumber)) {
			infLong = Long.parseLong(informNumber);
		}
		map.put("success", true);
		map.put("rows", productionProcessService.selectByJitaiIdAndIssueStateAndInformNumber(jitaiId, state, infLong));
		return map;
	}

	/**
	 * 根据机台id和通知单号查询生产加工单
	 * 
	 * @return
	 */
	@RequestMapping("/selectAllByInformAndJitaiId")
	public Map<String, Object> selectAllByInformAndJitaiId(Integer jitai, String informNumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long infor = null;
		if (StringUtil.isNotEmpty(informNumber)) {
			infor = Long.parseLong(informNumber);
		}
		map.put("success", true);
		map.put("rows", productionProcessService.selectAllByInformAndJitaiId(jitai, infor));
		return map;
	}

	/**
	 * 根据下发状态查询生产加工单
	 * 
	 * @param issueState
	 * @return
	 */
	@RequestMapping("/selectByIssueState")
	public List<ProductionProcess> selectByIssueState(String issueState) {
		System.out.println(issueState);
		if (StringUtil.isNotEmpty(issueState)) {
			issueState = "%" + issueState + "%";
		} else {
			issueState = "%";
		}
		return productionProcessService.selectByIssueState(issueState);
	}

	/**
	 * 根据条件查询生产加工单
	 * 
	 * @return
	 */
	@RequestMapping("/screen")
	public Map<String, Object> screen(String allorTime, Integer jitai) {
		Map<String, Object> map = new HashMap<String, Object>();
		logService.save(new Log(Log.SEARCH_ACTION, "查询生产通知单"));
		map.put("rows", productionProcessService.screen(allorTime, jitai));
		map.put("success", true);
		return map;
	}
	
	/**
	 * 查询该机台所有未完成的生产加工单
	 * @return
	 */
	@RequestMapping("/selectNoAccomplish")
	public Map<String, Object> selectNoAccomplish(Integer jitaiId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		Set<Long> informNumber = new HashSet<Long>();
		List<ProductionProcess> selectNoAccomplish = productionProcessService.selectNoAccomplish(jitaiId);
		for (ProductionProcess productionProcess : selectNoAccomplish) {
			informNumber.add(productionProcess.getInformNumber());
		}
		map.put("rows", informNumber);
		return map;
	}
}
