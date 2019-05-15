package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.shenke.entity.Pack;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.entity.Clerk;
import com.shenke.entity.Log;
import com.shenke.service.ClerkService;
import com.shenke.service.LogService;

/**
 * 员工管理Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/clerk")
public class ClerkAdminController {

	@Resource
	private ClerkService clerkService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询员工信息
	 * 
	 * @param
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = { "部门职员" })
	public Map<String, Object> list(Clerk clerk, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Clerk> goodsList = clerkService.list(clerk, page, rows, Direction.ASC, "id");
		Long total = clerkService.getCount(clerk);
		resultMap.put("rows", goodsList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询部门信息"));
		return resultMap;
	}

	/**
	 * 添加或者修改员工信息
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@RequiresPermissions(value = "部门职员")
	public Map<String, Object> save(Clerk clerk) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		if (clerk.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "更新员工信息" + clerk));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加员工信息" + clerk));
		}
		clerkService.save(clerk);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除员工信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "部门职员")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		Clerk clerk = clerkService.findById(id);
		logService.save(new Log(Log.DELETE_ACTION, "删除员工信息" + clerk));
		clerkService.deleteById(id);
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
	@RequestMapping("/clerkList")
	public List<Clerk> comboList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		return clerkService.findByName("%" + q + "%");
	}

	/**
	 * 下拉框模糊查询生产员工
	 *
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/clerkProList")
	public List<Clerk> clerkProList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		return clerkService.findByProName("%" + q + "%");
	}

	@RequestMapping("/findByName")
	public Clerk findByName(String clerk) {
		return clerkService.finName(clerk);
	}
	
}
