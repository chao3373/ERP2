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
import com.shenke.entity.Entrepot;
import com.shenke.entity.Log;
import com.shenke.service.EntrepotService;
import com.shenke.service.LogService;

/**
 * 仓库设置Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/entrepot")
public class EntrepotAdminController {

	@Resource
	private EntrepotService entrepotService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询仓位信息
	 * 
	 * @param goods
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = { "仓位设置" })
	public Map<String, Object> list(Entrepot Entrepot, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Entrepot> goodsList = entrepotService.list(Entrepot, page, rows, Direction.ASC, "id");
		Long total = entrepotService.getCount(Entrepot);
		resultMap.put("rows", goodsList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询仓位信息"));
		System.out.println(resultMap);
		return resultMap;
	}

	/**
	 * 添加或者修改仓位信息
	 * 
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@RequiresPermissions(value = "仓位设置")
	public Map<String, Object> save(Entrepot Entrepot) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		if (Entrepot.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "更新仓位信息" + Entrepot));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加仓位信息" + Entrepot));
		}
		entrepotService.save(Entrepot);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除仓位信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "仓位设置")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		Entrepot Entrepot = entrepotService.findById(id);
		logService.save(new Log(Log.DELETE_ACTION, "删除仓位信息" + Entrepot));
		entrepotService.deleteById(id);
		resultMap.put("success", true);
		return resultMap;
	}

}
