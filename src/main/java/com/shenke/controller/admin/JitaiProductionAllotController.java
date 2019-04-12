package com.shenke.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shenke.entity.JiTai;
import com.shenke.entity.JitaiProductionAllot;
import com.shenke.entity.Log;
import com.shenke.service.JiTaiService;
import com.shenke.service.JitaiProductionAllotService;
import com.shenke.service.LogService;
import com.shenke.service.SaleListProductService;

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

	@Resource
	private LogService logService;

	@Resource
	private JiTaiService jiTaiService;

	@Resource
	private SaleListProductService saleListProductService;

	/**
	 * 查询所有生产通知单
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public List<JitaiProductionAllot> list() {
		logService.save(new Log(Log.SEARCH_ACTION, "查询生产通知单"));
		return jitaiProductionAllotService.list();
	}

	/**
	 * 分组查询所有生产通知单
	 * 
	 * @return
	 */
	@RequestMapping("/listGroubBy")
	public List<JitaiProductionAllot> listGroubBy() {
		logService.save(new Log(Log.SEARCH_ACTION, "查询生产通知单"));
		return jitaiProductionAllotService.listGroubBy();
	}

	/**
	 * 根据条件查询生产通知单信息
	 * 
	 * @return
	 */
	@RequestMapping("/screen")
	public Map<String, Object> screen(String allorTime, Integer jitai) {
		Map<String, Object> map = new HashMap<String, Object>();
		logService.save(new Log(Log.SEARCH_ACTION, "查询生产通知单"));
		map.put("rows", jitaiProductionAllotService.screen(allorTime, jitai));
		map.put("success", true);
		return map;
	}

	/**
	 * 生产通知单下发
	 * 
	 * @return
	 */
	@RequestMapping("/issue")
	public Map<String, Object> issue(String idStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] ids = idStr.split(",");
		for (int i = 0; i < ids.length; i++) {
			logService.save(new Log(Log.UPDATE_ACTION, "下发机台"));
			JitaiProductionAllot jitaiProductionAllot = jitaiProductionAllotService.findById(Integer.parseInt(ids[i]));
			saleListProductService.updateState("下发机台：" + jitaiProductionAllot.getJiTai().getName(),
					jitaiProductionAllot.getSaleListProduct().getId());
			jitaiProductionAllotService.issue(" 下发机台：" + jitaiProductionAllot.getJiTai().getName(),
					Integer.parseInt(ids[i]));
		}
		map.put("success", true);
		return map;
	}

	/**
	 * 根据通知单号修改所分配的机台
	 * 
	 * @return
	 */
	@RequestMapping("/alertJitai")
	public Map<String, Object> alertJitai(String informNumberStr, Integer jitai) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] informNumbers = informNumberStr.split(",");
		System.out.println(informNumbers.length);
		List<JitaiProductionAllot> jitaiProductionAllots = new ArrayList<JitaiProductionAllot>();
		JiTai jiTai2 = jiTaiService.findById(jitai);
		Set<Integer> saleListIds = new HashSet<Integer>();
		for (int i = 0; i < informNumbers.length; i++) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改分配机台"));
			jitaiProductionAllots
					.addAll(jitaiProductionAllotService.findByImformNubers(Integer.parseInt(informNumbers[i])));
			jitaiProductionAllotService.updateJitai(Integer.parseInt(informNumbers[i]), jitai);
		}
		for (JitaiProductionAllot jitaiProductionAllot : jitaiProductionAllots) {
			saleListIds.add(jitaiProductionAllot.getSaleListProduct().getId());
		}
		for (Integer integer : saleListIds) {
			saleListProductService.updateState("分配机台：" + jiTai2.getName(), integer);
		}
		map.put("success", true);
		return map;
	}

}
