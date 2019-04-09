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
import com.shenke.entity.Brand;
import com.shenke.entity.Log;
import com.shenke.service.BrandService;
import com.shenke.service.LogService;

/**
 * 商标Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/brand")
public class BrandAdminController {

	@Resource
	private BrandService brandService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询商标信息
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = "商标设置")
	public Map<String, Object> list(Brand brand, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Brand> entrepotList = brandService.list(brand, page, rows, Direction.ASC, "id");
		Long total = brandService.getCount(brand);
		resultMap.put("rows", entrepotList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询商标信息"));
		return resultMap;
	}

	/**
	 * 添加或修改仓库信息
	 */
	@RequestMapping("/save")
	@RequiresPermissions("商标设置")
	public Map<String, Object> save(Brand dao) {
		if (dao.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改商标信息" + dao));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加商标信息" + dao));
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (dao.getId() == null) {
			if (brandService.findByBrandName(dao.getName()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "商标已经存在！");
				return resultMap;
			}
		}
		brandService.save(dao);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除商标信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "商标设置")
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除商标信息" + brandService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		brandService.delete(id);
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
	@RequestMapping("/brandList")
	public List<Brand> comboList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		return brandService.findByName("%" + q + "%");
	}
}
