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
import com.shenke.entity.Location;
import com.shenke.service.LocationService;
import com.shenke.service.LogService;
/**
 * 仓位信息controller
 * @author shao
 *
 */
@RestController
@RequestMapping("/admin/location")
public class LocationAdminController {

	@Resource
	private LocationService locationService;
	
	@Resource
	private LogService logService;
	
	/**
	 * 分页查询仓位信息
	 * 
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value="仓位管理")
	public Map<String, Object> list(Location location,@RequestParam(value = "page",required = false) Integer page,
			@RequestParam(value = "rows",required = false) Integer rows) throws Exception{
		Map<String, Object> result = new HashMap<>();
		List<Location> pList= locationService.list(location, page,rows,Direction.ASC, "id");
		Long total = locationService.getCount(location);
		result.put("rows",pList);
		result.put("total", total);
		//logService.save(new Log(Log.SEARCH_ACTION ,"查询盘点机信息"));
		return result;
	}
	
	/**
	 * 添加或修改盘点机信息
	 * 
	 */
	@RequestMapping("/save")
	@RequiresPermissions("仓位管理")
	public Map<String, Object> save(Location location) {
		System.out.println(location);
		Map<String, Object> resultMap = new HashMap<>();
		locationService.save(location);
		resultMap.put("success", true);
		return resultMap;
	}
	
	
	/**
	 * 删除盘点机信息
	 * 
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value="仓位信息")
	public Map<String, Object> delete(Integer id){
		//logService.save(new Log(Log.DELETE_ACTION,"删除盘点机信息" + pandianjiService.findById(id)));
		Map<String, Object> resultMap = new HashMap<>();
		locationService.delete(id);
		resultMap.put("success", true);
		return resultMap;
	}
	
	/**
	 * 下拉模糊查询
	 * 
	 */
	@RequestMapping("/locationList")
	public List<Location> comboList(String q) throws Exception{
		if(q == null) {
			q="";
		}
		return locationService.findByName("%" + q +"%");//.findByPid("%" + q +"%");
	}
}
