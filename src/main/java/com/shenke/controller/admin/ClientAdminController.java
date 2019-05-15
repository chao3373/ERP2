package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.entity.Client;
import com.shenke.entity.Log;
import com.shenke.service.ClientService;
import com.shenke.service.LogService;

/**
 * 客户Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/client")
public class ClientAdminController {

	@Resource
	private ClientService clientService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询产品及原料信息
	 * 
	 * @param
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = { "产品及原料设置" })
	public Map<String, Object> list(Client Client, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Client> goodsList = clientService.list(Client, page, rows, Direction.ASC, "id");
		Long total = clientService.getCount(Client);
		resultMap.put("rows", goodsList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询产品及原料信息"));
		System.out.println(resultMap);
		return resultMap;
	}

	/**
	 * 添加或者修改产品及原料信息
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@RequiresPermissions(value = "产品及原料设置")
	public Map<String, Object> save(Client Client) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		if (Client.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "更新产品及原料信息" + Client));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加产品及原料信息" + Client));
		}
		clientService.save(Client);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除部门信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "产品及原料设置")
	public Map<String, Object> delete(Integer id) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Client Client = clientService.findById(id);
			
			logService.save(new Log(Log.DELETE_ACTION, "删除产品及原料信息" + Client));
			clientService.deleteById(id);
			
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("errorInfo", "与该客户存在业务往来，无法删除！");
			resultMap.put("success", false);
			return resultMap;
		}
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 下拉框模糊查询
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping("/clientList")
	public List<Client> comboList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		return clientService.findByName("%" + q + "%");
	}

	/***
	 * 根据name查询
	 * @param name
	 * @return
	 */
	@RequestMapping("/findByName")
	public Client findByName(String name) {
		return clientService.findName(name);
	}
}
