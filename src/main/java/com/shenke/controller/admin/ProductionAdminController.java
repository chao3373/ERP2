package com.shenke.controller.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.entity.JitaiProductionAllot;
import com.shenke.entity.Log;
import com.shenke.entity.SaleListProduct;
import com.shenke.service.JiTaiService;
import com.shenke.service.JitaiProductionAllotService;
import com.shenke.service.LogService;
import com.shenke.service.SaleListProductService;

/**
 * 生产订单Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/production")
public class ProductionAdminController {

	@Resource
	private JiTaiService jiTaiService;

	@Resource
	private SaleListProductService saleListProductService;

	@Resource
	private LogService logService;

	@Resource
	private JitaiProductionAllotService jitaiProductionAllotService;

	/**
	 * 分配机台信息
	 * 
	 * @return
	 */
	@RequestMapping("/jitaiAllocation")
	public Map<String, Object> jitaiAllocation(String ids, Integer jitai) {
		Map<String, Object> map = new HashMap<String, Object>();

		String jitaiName = jiTaiService.findById(jitai).getName();
		String[] idarr = ids.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		logService.save(new Log(Log.UPDATE_ACTION, "分配机台"));

		for (int i = 0; i < idarr.length; i++) {
			int id = Integer.parseInt(idarr[i]);
			idList.add(id);
			saleListProductService.auditFailure(id, "分配机台：" + jitaiName);
		}

		List<SaleListProduct> list = saleListProductService.fandAll(idList);

		for (SaleListProduct saleListProduct : list) {
			logService.save(new Log(Log.ADD_ACTION, "添加生产通知单"));
			JitaiProductionAllot jitaiProductionAllot = new JitaiProductionAllot();
			jitaiProductionAllot.setJiTai(jiTaiService.findById(jitai));
			jitaiProductionAllot.setInformNumber(this.getInformNumber());
			jitaiProductionAllot.setSaleNumber(saleListProduct.getSaleList().getSaleNumber());
			jitaiProductionAllot.setProductionMessage("幅宽： " + saleListProduct.getModel() + "，厚度："
					+ saleListProduct.getPrice() + "，长度：" + saleListProduct.getLength() + "，颜色："
					+ saleListProduct.getColor() + "，数量：" + saleListProduct.getNum());
			jitaiProductionAllot.setTaskQuantity(saleListProduct.getSumwight());
			jitaiProductionAllot.setAllorTime(new Date(System.currentTimeMillis()));
			jitaiProductionAllot.setAllotState(saleListProduct.getState());
			jitaiProductionAllot.setIssueState("未下发");
			jitaiProductionAllotService.save(jitaiProductionAllot);
		}

		map.put("success", true);
		return map;
	}

	/**
	 * 生成通知单号
	 * 
	 * @return
	 */
	public Long getInformNumber() {
		if (jitaiProductionAllotService.findMaxInfornNumber() != null) {
			return jitaiProductionAllotService.findMaxInfornNumber() + 1;
		} else {
			return 1L;
		}
	}
}
