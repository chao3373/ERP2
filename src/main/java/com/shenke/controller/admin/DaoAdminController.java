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
import com.shenke.entity.Dao;
import com.shenke.entity.JiTai;
import com.shenke.entity.Log;
import com.shenke.service.DaoService;
import com.shenke.service.LogService;

/**
 * 剖刀Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/dao")
public class DaoAdminController {

	@Resource
	private DaoService daoService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询剖刀信息
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = "剖刀设置")
	public Map<String, Object> list(Dao dao, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Dao> entrepotList = daoService.list(dao, page, rows, Direction.ASC, "id");
		Long total = daoService.getCount(dao);
		resultMap.put("rows", entrepotList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询剖刀信息"));
		return resultMap;
	}

	/**
	 * 添加或修改仓库信息
	 */
	@RequestMapping("/save")
	@RequiresPermissions("剖刀设置")
	public Map<String, Object> save(Dao dao) {
		if (dao.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改剖刀信息" + dao));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加剖刀信息" + dao));
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (dao.getId() == null) {
			if (daoService.findByDaoName(dao.getName()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "剖刀已经存在！");
				return resultMap;
			}
		}
		daoService.save(dao);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除剖刀信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "剖刀设置")
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除剖刀信息" + daoService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		daoService.delete(id);
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
	@RequestMapping("/daoList")
	public List<Dao> comboList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		return daoService.findByName("%" + q + "%");
	}
}
