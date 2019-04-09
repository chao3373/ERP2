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
import com.shenke.entity.Dep;
import com.shenke.entity.Log;
import com.shenke.service.ClerkService;
import com.shenke.service.DepService;
import com.shenke.service.LogService;

/**
 * 部门管理Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/dep")
public class DepAdminController {

	@Resource
	private DepService depService;

	@Resource
	private ClerkService clerkService;

	@Resource
	private LogService logService;

	/**
	 * 根据父节点获取所有复选框权限菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loadTreeInfo")
	@RequiresPermissions(value = { "部门职员" })
	public String loadTreeInfo() throws Exception {
		logService.save(new Log(Log.SEARCH_ACTION, "查询所有商品类别信息"));
		return getAllByParentId(-1).toString();
	}

	/**
	 * 根据父节点id和权限菜单id集合获取所有复选框菜单集合
	 * 
	 * @param parentId
	 * @return
	 */
	public JsonArray getAllByParentId(Integer parentId) {
		JsonArray jsonArray = this.getByParentId(parentId);
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObject = (JsonObject) jsonArray.get(i);
			if ("open".equals(jsonObject.get("state").getAsString())) {
				continue;
			} else {
				jsonObject.add("children", getAllByParentId(jsonObject.get("id").getAsInt()));
			}
		}
		return jsonArray;
	}

	/**
	 * 根据父节点查询所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	public JsonArray getByParentId(Integer parentId) {
		List<Dep> goodsTypeList = depService.findByParentId(parentId);
		JsonArray jsonArray = new JsonArray();
		for (Dep goodsType : goodsTypeList) {
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
	@RequiresPermissions(value = { "部门职员" })
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		if (clerkService.findByDepId(id).size() == 0) {
			Dep goodsType = depService.findById(id);
			if (depService.findByParentId(goodsType.getpId()).size() == 1) {
				Dep parentGoodsType = depService.findById(goodsType.getpId());
				parentGoodsType.setState(0);
				depService.save(parentGoodsType);
			}
			logService.save(new Log(Log.DELETE_ACTION, "删除商品类别信息" + goodsType));
			depService.delete(id);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
			resultMap.put("errorInfo", "该类别下含有商品，不能删除！");
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
	@RequiresPermissions(value = {"部门职员"})
	public Map<String, Object> save(String name, Integer parentId) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		Dep goodsType = new Dep();
		goodsType.setName(name);
		goodsType.setpId(parentId);
		goodsType.setIcon("icon-folder");
		goodsType.setState(0);
		depService.save(goodsType);

		Dep parentGoodsType = depService.findById(parentId);
		parentGoodsType.setState(1);
		depService.save(parentGoodsType);

		logService.save(new Log(Log.ADD_ACTION, "添加商品类别信息"));
		resultMap.put("success", true);
		return resultMap;
	}

}
