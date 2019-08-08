package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shenke.entity.Log;
import com.shenke.entity.Pandianji;
import com.shenke.entity.Sell;
import com.shenke.service.LogService;
import com.shenke.service.PandianjiService;
import com.shenke.service.SellService;

/**
 * 
 * 盘点机设置Controller
 * 
 * 
 * */
@RestController
@RequestMapping("/admin/pandianji")
public class PandianjiAdminController {
	
	@Resource
	private PandianjiService pandianjiService;
	
	@Resource
	private LogService logService;

	/**
	 * 分页查询销售方式
	 * 
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value="盘点机管理")
	public Map<String, Object> list(Pandianji pandianji,@RequestParam(value = "page",required = false) Integer page,
			@RequestParam(value = "rows",required = false) Integer rows) throws Exception{
		Map<String, Object> result = new HashMap<>();
		List<Pandianji> pList= pandianjiService.list(pandianji, page,rows,Direction.ASC, "id");
		Long total = pandianjiService.getCount(pandianji);
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
	@RequiresPermissions("盘点机管理")
	public Map<String, Object> save(Pandianji pandianji) {
		/*if (pandianji.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改销售方式" + pandianji));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加销售方式" + pandianji));
		}*/
		Map<String, Object> resultMap = new HashMap<>();
		if (pandianji.getId() == null) {
			if (pandianjiService.findByPandianjiName(pandianji.getName()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "盘点机名称已经存在！");
				return resultMap;
			}
		}
		pandianjiService.save(pandianji);
		resultMap.put("success", true);
		return resultMap;
	}
	
	
	/**
	 * 删除盘点机信息
	 * 
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value="盘点机信息")
	public Map<String, Object> delete(Integer id){
		//logService.save(new Log(Log.DELETE_ACTION,"删除盘点机信息" + pandianjiService.findById(id)));
		Map<String, Object> resultMap = new HashMap<>();
		pandianjiService.delete(id);
		resultMap.put("success", true);
		return resultMap;
	}
	
	/**
	 * 下拉模糊查询
	 * 
	 */
	@RequestMapping("/pandianjiList")
	public List<Pandianji> comboList(String q) throws Exception{
		if(q == null) {
			q="";
		}
		return pandianjiService.findByPid("%" + q +"%");//.findByPid("%" + q +"%");
	}

	//根据序列号查询盘点机名称
	@RequestMapping("/findbyPid")
	public String findbyPid(String pid){
		return pandianjiService.findbyPid(pid);
	}

	
}


















