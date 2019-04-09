package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shenke.entity.ClientType;
import com.shenke.entity.Log;
import com.shenke.service.ClientService;
import com.shenke.service.ClientTypeService;
import com.shenke.service.LogService;

/**
 * 客户关系Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/clienttype")
public class ClientTypeAdminController {

	@Resource
	private ClientTypeService clientTypeService;

	@Resource
	private LogService logService;

	@Resource
	private ClientService clientService;

	/**
	 * 根据父节点获取所有复选框权限菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loadTreeInfo")
	@RequiresPermissions(value = { "客户设置" })
	public String loadTreeInfo() throws Exception {
		logService.save(new Log(Log.SEARCH_ACTION, "查询所有客户信息"));
		System.out.println("查询所有客户信息");
		return getAllByParentId(-1).toString();
	}

	/**
	 *根据父节点id和权限菜单id集合获取所有复选框菜单集合
	 * 
	 * @param parentId
	 * @return
	 */
	public JsonArray getAllByParentId(Integer parentId) {
		System.out.println("getAllByParentId=====" + parentId);
		JsonArray jsonArray = this.getByParentId(parentId);
		System.out.println(jsonArray);
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObject = (JsonObject) jsonArray.get(i);
			if ("open".equals(jsonObject.get("state").getAsString())) {
				continue;
			} else {
				jsonObject.add("children", getAllByParentId(jsonObject.get("id").getAsInt()));
				System.out.println(jsonObject);
			}
		}
		System.out.println(jsonArray);
		return jsonArray;
	}

	/**
	 * 根据父节点查询所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	public JsonArray getByParentId(Integer parentId) {
		System.out.println("getByParentId=====" + parentId);
		List<ClientType> goodsTypeList = clientTypeService.findByParentId(parentId);
		System.out.println(goodsTypeList);
		System.out.println(goodsTypeList.size());
		JsonArray jsonArray = new JsonArray();
		for (ClientType goodsType : goodsTypeList) {
			System.out.println(goodsType);
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id", goodsType.getId()); // 节点Id
			jsonObject.addProperty("text", goodsType.getName()); // 节点名称
			if (goodsType.getState() == 1) {
				jsonObject.addProperty("state", "closed"); // 根节点
			} else {
				jsonObject.addProperty("state", "open"); // 叶子节点
			}
			jsonObject.addProperty("iconCls", goodsType.getIcon()); // 节点图标
			JsonObject attributeObject = new JsonObject(); // 扩展属性
			attributeObject.addProperty("state", goodsType.getState()); // 节点状态
			jsonObject.add("attributes", attributeObject);
			jsonArray.add(jsonObject);
		}
		System.out.println(jsonArray);
		return jsonArray;
	}

	/**
	 * 删除部门
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = { "客户设置" })
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		if (clientService.findByClientTypeId(id).size() == 0) {
			ClientType goodsType = clientTypeService.findById(id);
			if (clientTypeService.findByParentId(goodsType.getpId()).size() == 1) {
				ClientType parentGoodsType = clientTypeService.findById(goodsType.getpId());
				parentGoodsType.setState(0);
				clientTypeService.save(parentGoodsType);
			}
			logService.save(new Log(Log.DELETE_ACTION, "删除客户信息" + goodsType));
			clientTypeService.delete(id);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
			resultMap.put("errorInfo", "该类别下含有信息，不能删除！");
		}
		return resultMap;
	}

	/**
	 * 添加部门信息
	 * 
	 * @param name
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@RequiresPermissions(value = { "客户设置" })
	public Map<String, Object> save(String name, Integer parentId) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		ClientType goodsType = new ClientType();
		goodsType.setName(name);
		goodsType.setpId(parentId);
		goodsType.setIcon("icon-folder");
		goodsType.setState(0);
		clientTypeService.save(goodsType);

		ClientType parentGoodsType = clientTypeService.findById(parentId);
		parentGoodsType.setState(1);
		clientTypeService.save(parentGoodsType);

		logService.save(new Log(Log.ADD_ACTION, "添加客户信息"));
		resultMap.put("success", true);
		return resultMap;
	}

}
