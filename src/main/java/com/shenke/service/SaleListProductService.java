package com.shenke.service;

import java.util.List;
import java.util.Map;

import com.shenke.entity.SaleListProduct;

public interface SaleListProductService {

	/**
	 * 根据销售单id查询所有销售单商品
	 * @param saleListId
	 * @return
	 */
	public List<SaleListProduct> listBySaleListId(Integer saleListId);

	/**
	 * 根据SaleListProductId删除该订单的所有商品信息
	 * @param id
	 */
	public void deleteBySaleListId(Integer id);

	/**
	 * 订单审核通过
	 * @param id
	 */
	public void passTheAudit(int id);

	/**
	 * 根据id修改订单状态
	 * @param id
	 */
	public void auditFailure(int id, String cause);

	/**
	 * 查询所有销售商品信息
	 * @return
	 */
	public List<SaleListProduct> fandAll();

	/**
	 * 查询所有的审核成功的销售商品信息
	 * 
	 */
	public List<SaleListProduct> listProductSucceed();

	/**
	 * 下拉框模糊查询所有幅宽信息
	 * @param q
	 * @return
	 */
	public List<SaleListProduct> breadthList(String q);

	/**
	 * 根据条件查询所有订单商品信息
	 * @param condition
	 * @return
	 */
	public List<SaleListProduct> screen(Map<String, Object> condition);

}
