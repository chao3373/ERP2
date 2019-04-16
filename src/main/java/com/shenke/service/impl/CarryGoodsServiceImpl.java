package com.shenke.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.shenke.entity.CarryGoods;
import com.shenke.repository.CarryGoodsRepository;
import com.shenke.service.CarryGoodsService;

/**
 * 提货单Service实现类
 * @author Administrator
 *
 */
@Service("carryGoodsService")
public class CarryGoodsServiceImpl implements CarryGoodsService{
	
	@Resource
	private CarryGoodsRepository carryGoodsRepository; 
	
	@Override
	public List<CarryGoods> fandAll() {
		return carryGoodsRepository.findAll();
	}
	
	
	public void add() {
		for (int i = 0; i < 88; i++) {
			CarryGoods carryGoods = new CarryGoods();
			carryGoods.setClientName("王五");
			carryGoods.setWeight(12.3);
			carryGoodsRepository.save(carryGoods);
		}
	}
	
}
