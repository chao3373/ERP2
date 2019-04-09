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
import com.shenke.entity.Pack;
import com.shenke.entity.Log;
import com.shenke.service.LogService;
import com.shenke.service.PackService;

/**
 * 包装设置Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/pack")
public class PackAdminController {

	@Resource
	private PackService packService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询包装信息
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = "包装设置")
	public Map<String, Object> list(Pack pack, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Pack> entrepotList = packService.list(pack, page, rows, Direction.ASC, "id");
		Long total = packService.getCount(pack);
		resultMap.put("rows", entrepotList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询包装信息"));
		return resultMap;
	}

	/**
	 * 添加或修改仓库信息
	 */
	@RequestMapping("/save")
	@RequiresPermissions("包装设置")
	public Map<String, Object> save(Pack pack) {
		if (pack.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改包装信息" + pack));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加包装信息" + pack));
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (pack.getId() == null) {
			if (packService.findByPackName(pack.getName()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "包装已经存在！");
				return resultMap;
			}
		}
		packService.save(pack);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除包装信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "包装设置")
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除包装信息" + packService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		packService.delete(id);
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
	@RequestMapping("/packList")
	public List<Pack> comboList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		return packService.findByName("%" + q + "%");
	}
}
