package com.shenke.controller.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.entity.JiTai;
import com.shenke.entity.JitaiProductionAllot;
import com.shenke.entity.Log;
import com.shenke.entity.ProductionProcess;
import com.shenke.entity.SaleListProduct;
import com.shenke.service.JiTaiService;
import com.shenke.service.JitaiProductionAllotService;
import com.shenke.service.LogService;
import com.shenke.service.ProductionProcessService;
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

	@Resource
	private ProductionProcessService productionProcessService;

	/**
	 * 分配机台信息
	 * 
	 * @return
	 */
	@RequestMapping("/jitaiAllocation")
	public Map<String, Object> jitaiAllocation(String ids, Integer jitai) {
		Map<String, Object> map = new HashMap<String, Object>();

		JiTai jitai1 = jiTaiService.findById(jitai);
		String[] idarr = ids.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		logService.save(new Log(Log.UPDATE_ACTION, "分配机台"));

		for (int i = 0; i < idarr.length; i++) {
			int id = Integer.parseInt(idarr[i]);
			idList.add(id);
			saleListProductService.auditFailure(id, "分配机台：" + jitai1.getName());
		}

		List<SaleListProduct> list = saleListProductService.fandAll(idList);

		Long informNumber = this.getInformNumber();
		for (SaleListProduct saleListProduct : list) {

			ProductionProcess processpProcess = new ProductionProcess();
			processpProcess.setSaleListProduct(saleListProduct);
			processpProcess.setBrand(saleListProduct.getBrand());
			processpProcess.setClientname(saleListProduct.getClientname());
			processpProcess.setColor(saleListProduct.getColor());
			processpProcess.setDao(saleListProduct.getDao());
			processpProcess.setDemand(saleListProduct.getDemand());
			processpProcess.setJiTai(jitai1);
			processpProcess.setLabel(saleListProduct.getLabel());
			processpProcess.setLength(saleListProduct.getLength());
			processpProcess.setLetter(saleListProduct.getLetter());
			processpProcess.setMeter(saleListProduct.getMeter());
			processpProcess.setModel(saleListProduct.getModel());
			processpProcess.setName(saleListProduct.getName());
			processpProcess.setNum(saleListProduct.getNum());
			processpProcess.setNumsquare(saleListProduct.getNumsquare());
			processpProcess.setOneweight(saleListProduct.getOneweight());
			processpProcess.setPack(saleListProduct.getPack());
			processpProcess.setPatu(saleListProduct.getPatu());
			processpProcess.setPeasant(saleListProduct.getPeasant());
			processpProcess.setPrice(saleListProduct.getPrice());
			processpProcess.setRealitymodel(saleListProduct.getRealitymodel());
			processpProcess.setRealityprice(saleListProduct.getRealityprice());
			processpProcess.setRealityweight(saleListProduct.getRealityweight());
			processpProcess.setRemark(saleListProduct.getRemark());
			processpProcess.setSquare(saleListProduct.getSquare());
			processpProcess.setState(saleListProduct.getState());
			processpProcess.setSumwight(saleListProduct.getSumwight());
			processpProcess.setTheoryweight(saleListProduct.getTheoryweight());
			processpProcess.setWeight(saleListProduct.getWeight());
			processpProcess.setWeightway(saleListProduct.getWeightway());
			processpProcess.setWightset(saleListProduct.getWightset());
			processpProcess.setInformNumber(informNumber);
			processpProcess.setIssueState("未下发");
			processpProcess.setAllotState("分配状态：" + jitai1.getName());
			processpProcess.setAllorTime(new Date(System.currentTimeMillis()));

			productionProcessService.save(processpProcess);

			logService.save(new Log(Log.ADD_ACTION, "添加生产通知单"));
			for (int i = 0; i < saleListProduct.getNum(); i++) {
				JitaiProductionAllot jitaiProductionAllot = new JitaiProductionAllot();
				jitaiProductionAllot.setJiTai(jiTaiService.findById(jitai));
				jitaiProductionAllot.setInformNumber(informNumber);
				jitaiProductionAllot.setSaleNumber(saleListProduct.getSaleList().getSaleNumber());
				jitaiProductionAllot.setProductionMessage("幅宽： " + saleListProduct.getModel() + "，厚度："
						+ saleListProduct.getPrice() + "，长度：" + saleListProduct.getLength() + "，颜色："
						+ saleListProduct.getColor() + "，要求：" + saleListProduct.getDemand());
				jitaiProductionAllot.setTaskQuantity(saleListProduct.getSumwight());
				jitaiProductionAllot.setAllorTime(new Date(System.currentTimeMillis()));
				jitaiProductionAllot.setAllotState(saleListProduct.getState());
				jitaiProductionAllot.setIssueState("未下发");
				jitaiProductionAllot.setSaleListProduct(saleListProduct);
				Integer countSaleListProduct = jitaiProductionAllotService
						.countBySaleListProductId(saleListProduct.getId());
				jitaiProductionAllot.setNum(countSaleListProduct == null ? 0 : countSaleListProduct);
				jitaiProductionAllotService.save(jitaiProductionAllot);
				List<JitaiProductionAllot> jitaiProductionAllots = jitaiProductionAllotService
						.findBySaleListProductId(saleListProduct.getId());
				for (JitaiProductionAllot jitaiProductionAllo : jitaiProductionAllots) {
					jitaiProductionAllotService.updateNum(countSaleListProduct,
							jitaiProductionAllo.getSaleListProduct().getId());
				}
			}
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
