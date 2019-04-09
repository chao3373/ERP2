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
import com.shenke.entity.Patu;
import com.shenke.entity.Log;
import com.shenke.service.LogService;
import com.shenke.service.PatuService;

/**
 * 纸管设置Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/patu")
public class PatuAdminController {

	@Resource
	private PatuService patuService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询纸管信息
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = "纸管设置")
	public Map<String, Object> list(Patu patu, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Patu> entrepotList = patuService.list(patu, page, rows, Direction.ASC, "id");
		Long total = patuService.getCount(patu);
		resultMap.put("rows", entrepotList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询纸管信息"));
		return resultMap;
	}

	/**
	 * 添加或修改仓库信息
	 */
	@RequestMapping("/save")
	@RequiresPermissions("纸管设置")
	public Map<String, Object> save(Patu patu) {
		if (patu.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改纸管信息" + patu));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加纸管信息" + patu));
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (patu.getId() == null) {
			if (patuService.findByPatuName(patu.getName()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "纸管已经存在！");
				return resultMap;
			}
		}
		patuService.save(patu);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除纸管信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "纸管设置")
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除纸管信息" + patuService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		patuService.delete(id);
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
	@RequestMapping("/patuList")
	public List<Patu> comboList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		return patuService.findByName("%" + q + "%");
	}
}
