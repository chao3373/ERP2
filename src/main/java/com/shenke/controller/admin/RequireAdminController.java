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
import com.shenke.entity.Log;
import com.shenke.entity.Require;
import com.shenke.service.LogService;
import com.shenke.service.RequireService;

/**
 * 要求设置Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/require")
public class RequireAdminController {

	@Resource
	private RequireService requireService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询要求信息
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = "要求设置")
	public Map<String, Object> list(Require Require, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Require> entrepotList = requireService.list(Require, page, rows, Direction.ASC, "id");
		Long total = requireService.getCount(Require);
		resultMap.put("rows", entrepotList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询要求信息"));
		return resultMap;
	}

	/**
	 * 添加或修改仓库信息
	 */
	@RequestMapping("/save")
	@RequiresPermissions("要求设置")
	public Map<String, Object> save(Require Require) {
		if (Require.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改要求信息" + Require));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加要求信息" + Require));
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (Require.getId() == null) {
			if (requireService.findByRequireName(Require.getName()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "要求已经存在！");
				return resultMap;
			}
		}
		requireService.save(Require);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除要求信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "要求设置")
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除要求信息" + requireService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		requireService.delete(id);
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
	@RequestMapping("/requireList")
	public List<Require> comboList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		return requireService.findByName("%" + q + "%");
	}
}
