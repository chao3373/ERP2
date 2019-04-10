package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.entity.Log;
import com.shenke.entity.SaleListProduct;
import com.shenke.service.ClientService;
import com.shenke.service.LogService;
import com.shenke.service.SaleListProductService;

@RestController
@RequestMapping("/admin/saleListProduct")
public class SaleListProductAdminController {

	@Resource
	private LogService logService;

	@Resource
	private ClientService clientService;

	@Resource
	private SaleListProductService saleListProductService;

	/**
	 * 订单审核通过
	 * 
	 * @return
	 */
	@RequestMapping("/passTheAudit")
	public Map<String, Object> passTheAudit(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			int id = Integer.parseInt(idArr[i]);

			logService.save(new Log(Log.AUDIT_ACTION, "审核通过"));
			saleListProductService.passTheAudit(id);
		}
		map.put("success", true);
		return map;
	}

	/**
	 * 订单审核失败
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/auditFailure")
	public Map<String, Object> auditFailure(String ids, String cause) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			int id = Integer.parseInt(idArr[i]);
			logService.save(new Log(Log.AUDIT_ACTION, "审核失败"));
			saleListProductService.auditFailure(id, cause);
		}
		map.put("success", true);
		return map;
	}

	/**
	 * 查询所有审核成功的销售商品信息
	 * 
	 * @return
	 */
	@RequestMapping("/listProductSucceed")
	public Map<String, Object> listProductSucceed() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", saleListProductService.listProductSucceed());
		return map;
	}

	/**
	 * 下拉框模糊查询所有幅宽
	 * 
	 * @return
	 */
	@RequestMapping("/breadthList")
	public List<SaleListProduct> breadthList(String q) {
		if (q == null) {
			q = "";
		}
		return saleListProductService.breadthList(q);
	}

	/**
	 * 根据条件查询所有订单商品信息
	 * 
	 * @param modeSort
	 * @param priceSort
	 * @param lengthSort
	 * @param client
	 * @return
	 */
	@RequestMapping("/screen")
	public Map<String, Object> screen(String modeSort, String priceSort, String lengthSort, String client, String meter,
			String oneweight, String sumwight, String realitymodel) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put("modeSort", modeSort);
		condition.put("priceSort", priceSort);
		condition.put("lengthSort", lengthSort);
		condition.put("client", client);
		condition.put("meter", meter);
		condition.put("oneweight", oneweight);
		condition.put("sumwight", sumwight);
		condition.put("realitymodel", realitymodel);

		List<SaleListProduct> screen = saleListProductService.screen(condition);
		map.put("success", true);
		map.put("rows", screen);
		return map;
	}
}
