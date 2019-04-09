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
import com.shenke.entity.Receipt;
import com.shenke.service.LogService;
import com.shenke.service.ReceiptService;

/**
 * 收付款方式Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/receipt")
public class ReceiptAdminController {

	@Resource
	private ReceiptService receiptService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询收付款方式
	 * 
	 * @param goods
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = { "收付款方式设置" })
	public Map<String, Object> list(Receipt Receipt, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Receipt> goodsList = receiptService.list(Receipt, page, rows, Direction.ASC, "id");
		Long total = receiptService.getCount(Receipt);
		resultMap.put("rows", goodsList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询收付款方式"));
		System.out.println(resultMap);
		return resultMap;
	}

	/**
	 * 添加或者修改收付款方式
	 * 
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@RequiresPermissions(value = "收付款方式设置")
	public Map<String, Object> save(Receipt Receipt) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		if (Receipt.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "更新收付款方式" + Receipt));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加收付款方式" + Receipt));
		}
		receiptService.save(Receipt);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除收付款方式
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "收付款方式设置")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		Receipt Receipt = receiptService.findById(id);
		logService.save(new Log(Log.DELETE_ACTION, "删除收付款方式" + Receipt));
		receiptService.deleteById(id);
		resultMap.put("success", true);
		return resultMap;
	}

}
