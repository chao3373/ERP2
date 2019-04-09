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
import com.shenke.entity.Log;
import com.shenke.entity.Product;
import com.shenke.service.LogService;
import com.shenke.service.ProductService;

/**
 * 产品及原料Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/product")
public class ProductAdminController {

	@Resource
	private ProductService productService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询产品及原料信息
	 * 
	 * @param goods
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = { "产品及原料设置" })
	public Map<String, Object> list(Product Product, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Product> goodsList = productService.list(Product, page, rows, Direction.ASC, "id");
		Long total = productService.getCount(Product);
		resultMap.put("rows", goodsList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询产品及原料信息"));
		System.out.println(resultMap);
		return resultMap;
	}

	/**
	 * 添加或者修改产品及原料信息
	 * 
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@RequiresPermissions(value = "产品及原料设置")
	public Map<String, Object> save(Product Product) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		if (Product.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "更新产品及原料信息" + Product));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加产品及原料信息" + Product));
		}
		productService.save(Product);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除产品及原料信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "产品及原料设置")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		Product Product = productService.findById(id);
		logService.save(new Log(Log.DELETE_ACTION, "删除产品及原料信息" + Product));
		productService.deleteById(id);
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
	@RequestMapping("/productList")
	public List<Product> comboList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		return productService.findByName("%" + q + "%");
	}
}
