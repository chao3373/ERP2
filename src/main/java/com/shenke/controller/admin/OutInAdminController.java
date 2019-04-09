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
import com.shenke.entity.OutIn;
import com.shenke.service.LogService;
import com.shenke.service.OutInService;

/**
 * 出入库设置Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/outin")
public class OutInAdminController {

	@Resource
	private OutInService outInService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询出入库信息
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = "出入库设置")
	public Map<String, Object> list(OutIn outIn, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<OutIn> entrepotList = outInService.list(outIn, page, rows, Direction.ASC, "id");
		Long total = outInService.getCount(outIn);
		resultMap.put("rows", entrepotList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询出入库信息"));
		return resultMap;
	}

	/**
	 * 添加或修改仓库信息
	 */
	@RequestMapping("/save")
	@RequiresPermissions("出入库设置")
	public Map<String, Object> save(OutIn outIn) {
		if (outIn.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改出入库信息" + outIn));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加出入库信息" + outIn));
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (outIn.getId() == null) {
			if (outInService.findByOutInName(outIn.getName()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "出入库已经存在！");
				return resultMap;
			}
		}
		outInService.save(outIn);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除出入库信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "出入库设置")
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除出入库信息" + outInService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		outInService.delete(id);
		resultMap.put("success", true);
		return resultMap;
	}

}
