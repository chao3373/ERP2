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
import com.shenke.entity.Wight;
import com.shenke.entity.Log;
import com.shenke.service.LogService;
import com.shenke.service.WightService;

/**
 * 重量设置Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/wight")
public class WightAdmniController {

	@Resource
	private WightService wightService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询重量信息
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = "重量设置")
	public Map<String, Object> list(Wight wight, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Wight> entrepotList = wightService.list(wight, page, rows, Direction.ASC, "id");
		Long total = wightService.getCount(wight);
		resultMap.put("rows", entrepotList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询重量信息"));
		return resultMap;
	}

	/**
	 * 添加或修改仓库信息
	 */
	@RequestMapping("/save")
	@RequiresPermissions("重量设置")
	public Map<String, Object> save(Wight wight) {
		if (wight.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改重量信息" + wight));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加重量信息" + wight));
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (wight.getId() == null) {
			if (wightService.findByWightName(wight.getName()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "重量已经存在！");
				return resultMap;
			}
		}
		wightService.save(wight);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除重量信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "重量设置")
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除重量信息" + wightService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		wightService.delete(id);
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
	@RequestMapping("/wightList")
	public List<Wight> comboList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		return wightService.findByName("%" + q + "%");
	}
}
