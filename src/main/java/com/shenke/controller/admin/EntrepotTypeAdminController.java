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
import com.shenke.entity.EntrepotType;
import com.shenke.entity.Log;
import com.shenke.entity.EntrepotType;
import com.shenke.service.EntrepotService;
import com.shenke.service.EntrepotTypeService;
import com.shenke.service.LogService;

/**
 * 仓位Type Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/entrepottype")
public class EntrepotTypeAdminController {

	@Resource
	private EntrepotTypeService entrepotTypeService;

	@Resource
	private EntrepotService entrepotService;

	@Resource
	private LogService logService;

	/**
	 * 根据父节点获取所有复选框权限菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loadTreeInfo")
	@RequiresPermissions(value = { "仓库设置" })
	public String loadTreeInfo() throws Exception {
		logService.save(new Log(Log.SEARCH_ACTION, "查询所有仓库信息"));
		return getAllByParentId(-1).toString();
	}

	/**
	 * 根据父节点id和权限菜单id集合获取所有菜单集合
	 * 
	 * @param parentId
	 * @return
	 */
	public JsonArray getAllByParentId(Integer parentId) {
		JsonArray jsonArray = this.getByParentId(parentId);
		JsonArray jsonArr = jsonArray;
		for (int i = 0; i < jsonArr.size(); i++) {
			JsonObject jsonObject = (JsonObject) jsonArr.get(i);
			if ("open".equals(jsonObject.get("state").getAsString())) {
				continue;
			} else {
				jsonObject.add("children", getAllByParentId(jsonObject.get("id").getAsInt()));
			}
		}
		return jsonArr;
	}

	/**
	 * 根据父节点查询所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	public JsonArray getByParentId(Integer parentId) {
		List<EntrepotType> goodsTypeList = entrepotTypeService.findByParentId(parentId);
		JsonArray jsonArray = new JsonArray();
		for (EntrepotType goodsType : goodsTypeList) {
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
	@RequiresPermissions(value = { "仓库设置" })
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		if (entrepotService.findByEntrepotTypeId(id).size() == 0) {
			EntrepotType goodsType = entrepotTypeService.findById(id);
			if (entrepotTypeService.findByParentId(goodsType.getpId()).size() == 1) {
				EntrepotType parentGoodsType = entrepotTypeService.findById(goodsType.getpId());
				parentGoodsType.setState(0);
				entrepotTypeService.save(parentGoodsType);
			}
			logService.save(new Log(Log.DELETE_ACTION, "删除仓库信息" + goodsType));
			entrepotTypeService.delete(id);
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
	@RequiresPermissions(value = { "仓库设置" })
	public Map<String, Object> save(String name, Integer parentId) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		EntrepotType goodsType = new EntrepotType();
		goodsType.setName(name);
		goodsType.setpId(parentId);
		goodsType.setIcon("icon-folder");
		goodsType.setState(0);
		entrepotTypeService.save(goodsType);

		EntrepotType parentGoodsType = entrepotTypeService.findById(parentId);
		parentGoodsType.setState(1);
		entrepotTypeService.save(parentGoodsType);

		logService.save(new Log(Log.ADD_ACTION, "添加仓库信息"));
		resultMap.put("success", true);
		return resultMap;
	}
}
