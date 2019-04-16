package com.shenke.service;

import java.util.List;

import com.shenke.entity.Storage;

/**
 * 入库单Service
 * @author Administrator
 *
 */
public interface StorageService {

	/**
	 * 添加入库单
	 * @param weight 实际重量
	 * @param saleListProductId 销售商品id
	 * @param jitaiProductionAllotId 生产通知单id
	 * @param producionProcessId 生产加工单id
	 * @param jitaiId 机台id
	 */
	public void add(Double weight, Integer saleListProductId, Integer jitaiProductionAllotId, Integer producionProcessId,
			Integer jitaiId);

	public List<Storage> fandAll();

}
