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
import com.shenke.entity.Group;
import com.shenke.entity.Log;
import com.shenke.service.GroupService;
import com.shenke.service.LogService;

/**
 * 班组设置Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/group")
public class GroupAdminController {

	@Resource
	private GroupService groupService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询班组信息
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = "班组设置")
	public Map<String, Object> list(Group group, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Group> entrepotList = groupService.list(group, page, rows, Direction.ASC, "id");
		Long total = groupService.getCount(group);
		resultMap.put("rows", entrepotList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询班组信息"));
		return resultMap;
	}

	/**
	 * 添加或修改仓库信息
	 */
	@RequestMapping("/save")
	@RequiresPermissions("班组设置")
	public Map<String, Object> save(Group group) {
		if (group.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改班组信息" + group));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加班组信息" + group));
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (group.getId() == null) {
			if (groupService.findByGroupName(group.getName()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "班组已经存在！");
				return resultMap;
			}
		}
		groupService.save(group);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除班组信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "班组设置")
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除班组信息" + groupService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		groupService.delete(id);
		resultMap.put("success", true);
		return resultMap;
	}
	
	/**
	 * 下拉框模糊查询
	 * @param q
	 * @return
	 */
	@RequestMapping("/clerkList")
	public List<Group> clerkList(String q) {
		if (q == null) {
			q = "";
		}
		return groupService.findByName("%" + q + "%");
	}

}
