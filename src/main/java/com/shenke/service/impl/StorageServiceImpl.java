package com.shenke.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenke.entity.JiTai;
import com.shenke.entity.JitaiProductionAllot;
import com.shenke.entity.ProductionProcess;
import com.shenke.entity.SaleListProduct;
import com.shenke.entity.Storage;
import com.shenke.repository.JiTaiRepository;
import com.shenke.repository.JitaiProductionAllotRepository;
import com.shenke.repository.ProductionProcessRepository;
import com.shenke.repository.SaleListProductRepository;
import com.shenke.repository.StorageRepository;
import com.shenke.service.StorageService;

/**
 * 入库单Service实现类
 * 
 * @author Administrator
 *
 */
@Service("storageService")
@Transactional
public class StorageServiceImpl implements StorageService {

	@Resource
	private StorageRepository storageRepository;

	@Resource
	private SaleListProductRepository saleListProductRepository;

	@Resource
	private JitaiProductionAllotRepository jitaiProductionAllotRepository;

	@Resource
	private ProductionProcessRepository productionProcessRepository;

	@Resource
	private JiTaiRepository jiTaiRepository;

	@Override
	public void add(Double weight, Integer saleListProductId, Integer jitaiProductionAllotId,
			Integer producionProcessId, Integer jitaiId) {

		SaleListProduct saleListProduct = saleListProductRepository.findOne(saleListProductId);
		JitaiProductionAllot jitaiProductionAllot = jitaiProductionAllotRepository.findOne(jitaiProductionAllotId);
		JiTai jiTai = jiTaiRepository.findOne(jitaiId);
		ProductionProcess productionProcess = productionProcessRepository.findOne(producionProcessId);
		Integer count = productionProcessRepository.findAccomplishNumberById(producionProcessId);
		if (count == null || count == 0) {
			count = 1;
			productionProcessRepository.updateAccomplishNumberById(count, producionProcessId);
		} else if (count == productionProcess.getNum() - 1) {
			count += 1;
			System.out.println("producionProcessId" + producionProcessId);
			productionProcessRepository.updateAccomplishNumberById(count, producionProcessId);
			productionProcessRepository.updateState("生产完成：" + jiTai.getName(), producionProcessId);
			saleListProductRepository.updateState("生产完成：" + jiTai.getName(), saleListProductId);
		} else {
			count += 1;
			productionProcessRepository.updateAccomplishNumberById(count, producionProcessId);
		}

		jitaiProductionAllotRepository.updateStateById("生产完成：" + jiTai.getName(), jitaiProductionAllotId);

		Storage storage = new Storage();

		storage.setAccomplishState("完成");
		storage.setAllorTime(jitaiProductionAllot.getAllorTime());
		storage.setAllotState(jitaiProductionAllot.getAllotState());
		storage.setBrand(saleListProduct.getBrand());
		storage.setClientname(saleListProduct.getClientname());
		storage.setColor(saleListProduct.getColor());
		storage.setDao(saleListProduct.getDao());
		storage.setDemand(saleListProduct.getDemand());
		storage.setInformNumber(jitaiProductionAllot.getInformNumber());
		storage.setIssueState(jitaiProductionAllot.getIssueState());
		storage.setJiTai(jiTai);
		storage.setJitaiProductionAllot(jitaiProductionAllot);
		storage.setLabel(saleListProduct.getLabel());
		storage.setLength(saleListProduct.getLength());
		storage.setLetter(saleListProduct.getLetter());
		storage.setMeter(saleListProduct.getMeter());
		storage.setModel(saleListProduct.getModel());
		storage.setName(saleListProduct.getName());
		storage.setNum(saleListProduct.getNum());
		storage.setNumsquare(saleListProduct.getNumsquare());
		storage.setOneweight(saleListProduct.getOneweight());
		storage.setPack(saleListProduct.getPack());
		storage.setPatu(saleListProduct.getPatu());
		storage.setPeasant(saleListProduct.getPeasant());
		storage.setPrice(saleListProduct.getPrice());
		storage.setProductionMessage(jitaiProductionAllot.getProductionMessage());
		storage.setRealitymodel(saleListProduct.getRealitymodel());
		storage.setRealityprice(saleListProduct.getRealityprice());
		storage.setRealityweight(weight);
		storage.setSaleList(saleListProduct.getSaleList());
		storage.setSaleListProduct(saleListProduct);
		storage.setSaleNumber(saleListProduct.getSaleList().getSaleNumber());
		storage.setSquare(saleListProduct.getSquare());
		storage.setState(saleListProduct.getState());
		storage.setSumwight(saleListProduct.getSumwight());
		storage.setTaskQuantity(jitaiProductionAllot.getTaskQuantity());
		storage.setTheoryweight(saleListProduct.getTheoryweight());
		storage.setWeight(saleListProduct.getWeight());
		storage.setWeightway(saleListProduct.getWeightway());
		storage.setWightset(saleListProduct.getWightset());

		storageRepository.save(storage);

	}

	@Override
	public List<Storage> fandAll() {
		return storageRepository.findAll();
	}

}
