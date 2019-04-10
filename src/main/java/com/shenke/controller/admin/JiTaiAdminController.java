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
import com.shenke.entity.JiTai;
import com.shenke.entity.Log;
import com.shenke.service.JiTaiService;
import com.shenke.service.LogService;

/**
 * 机台Controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/jitai")
public class JiTaiAdminController {
	
	@Resource
	private JiTaiService jiTaiService;
	
	@Resource
	private LogService logService;
	
	/**
	 * 分页查询机台信息
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = "机台设置")
	public Map<String, Object> list(JiTai jitai, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<JiTai> entrepotList = jiTaiService.list(jitai, page, rows, Direction.ASC, "id");
		Long total = jiTaiService.getCount(jitai);
		resultMap.put("rows", entrepotList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询机台信息"));
		return resultMap;
	}
	
	/**
	 * 查询所有所有机台信息
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<JiTai> findAll() {
		return jiTaiService.findAll();
	}

	/**
	 * 添加或修改仓库信息
	 */
	@RequestMapping("/save")
	@RequiresPermissions("机台设置")
	public Map<String, Object> save(JiTai jitai) {
		if (jitai.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改机台信息" + jitai));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加机台信息" + jitai));
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (jitai.getId() == null) {
			if (jiTaiService.findByJiTaiName(jitai.getName()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "机台已经存在！");
				return resultMap;
			}
		}
		jiTaiService.save(jitai);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除机台信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "机台设置")
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除机台信息" + jiTaiService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		jiTaiService.delete(id);
		resultMap.put("success", true);
		return resultMap;
	}
	
	
	/**
	 * 下拉框模糊查询机台信息
	 * @return
	 */
	@RequestMapping("/jitaiList")
	public List<JiTai> jitaiList(String q) {
		if(q==null){
			q="";
		}
		return jiTaiService.findByName("%"+q+"%");
	}
}
