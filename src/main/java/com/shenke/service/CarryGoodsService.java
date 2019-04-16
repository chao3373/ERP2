package com.shenke.service;

import java.util.List;

import com.shenke.entity.CarryGoods;

/**
 * 提货单Service
 * @author Administrator
 *
 */
public interface CarryGoodsService {

	/**
	 * 查询所有提货单信息
	 * @return
	 */
	public List<CarryGoods> fandAll();
	
	public void add();
	
}
