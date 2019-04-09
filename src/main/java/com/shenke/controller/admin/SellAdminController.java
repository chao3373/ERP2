package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shenke.entity.Client;
import com.shenke.entity.Log;
import com.shenke.entity.Sell;
import com.shenke.service.LogService;
import com.shenke.service.SellService;

/**
 * 销售方式Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/sell")
public class SellAdminController {

	@Resource
	private LogService logService;

	@Resource
	private SellService sellService;

	/**
	 * 分页查询销售方式
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = "销售方式")
	public Map<String, Object> list(Sell sell, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Sell> entrepotList = sellService.list(sell, page, rows, Direction.ASC, "id");
		Long total = sellService.getCount(sell);
		resultMap.put("rows", entrepotList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询销售方式"));
		return resultMap;
	}

	/**
	 * 添加或修改销售方式
	 */
	@RequestMapping("/save")
	@RequiresPermissions("销售方式")
	public Map<String, Object> save(Sell sell) {
		if (sell.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改销售方式" + sell));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加销售方式" + sell));
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (sell.getId() == null) {
			if (sellService.findBySellMethod(sell.getMethod()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "销售方式已经存在！");
				return resultMap;
			}
		}
		sellService.save(sell);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除销售方式
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "销售方式")
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除仓库信息" + sellService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		sellService.delete(id);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 下拉框模糊查询
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sellList")
	public List<Sell> comboList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		return sellService.findByName("%" + q + "%");
	}

}
