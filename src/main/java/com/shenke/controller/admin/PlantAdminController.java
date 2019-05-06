package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.shenke.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.entity.Log;
import com.shenke.entity.Plant;
import com.shenke.service.LogService;
import com.shenke.service.PlantService;

/**
 * 厂商设置Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/plant")
public class PlantAdminController {

	@Resource
	private PlantService plantService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询厂商信息
	 * 
	 * @param goods
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = { "厂商设置" })
	public Map<String, Object> list(Plant Plant, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Plant> goodsList = plantService.list(Plant, page, rows, Direction.ASC, "id");
		Long total = plantService.getCount(Plant);
		resultMap.put("rows", goodsList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询厂商信息"));
		System.out.println(resultMap);
		return resultMap;
	}

	/**
	 * 添加或者修改厂商信息
	 * 
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public Map<String, Object> save(Plant plant) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		if (plant.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "更新厂商信息" + plant));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加厂商信息" + plant));
		}
		plantService.save(plant);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除厂商信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "厂商设置")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		Plant Plant = plantService.findById(id);
		logService.save(new Log(Log.DELETE_ACTION, "删除厂商信息" + Plant));
		plantService.deleteById(id);
		resultMap.put("success", true);
		return resultMap;
	}

	/** 
	* @Description: 模糊查询所有厂商 
	* @Param:
	* @return:  
	* @Author: Andy
	* @Date:  
	*/
	@RequestMapping("/plantList")
	public List<Plant> plantList(String q) {
		if (StringUtil.isEmpty(q)) {
			q = "";
		}
		return plantService.findByName("%" + q + "%");
	}

}
