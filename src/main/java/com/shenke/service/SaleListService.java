package com.shenke.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.shenke.entity.SaleList;
import com.shenke.entity.SaleListProduct;

/**
 * 销售单Service接口
 * 
 * @author Administrator
 *
 */
public interface SaleListService {
	/**
	 * 获取当天最大销售单号
	 * 
	 * @return
	 */
	public String getTodayMaxSaleNumber();

	/**
	 * 添加销售单以及所有销售单中的商品
	 * @param saleList
	 * @param plgList
	 */
	public void save(SaleList saleList, List<SaleListProduct> plgList);

	/**
	 * 根据条件查询所有销售订单信息
	 * @param saleList
	 * @param desc
	 * @param
	 * @return
	 */
	public List<SaleList> list(SaleList saleList, Direction desc, String... properties);

	/**
	 *根据销售单id删除销售订单信息 
	 * @param id
	 */
	public void deleteByid(Integer id);

	/**
	 * 根据订单状态查询所有订单商品信息
	 * @param state
	 */
	public List<SaleListProduct> listProductByState(String state);

	/**
	 * 根据订单状态跟销售单id查询所有订单商品信息
	 * @param id
	 * @param state
	 */
	public List<SaleListProduct> listProductByStateAndId(Integer id, String state);

	/**
	 * 查询所有的包含未审核和审核失败的销售订单信息
	 * @param id
	 */
	public List<Integer> getSaleListNo(int id);

    public void saveOne(SaleList saleList);

    public void saveTwo(SaleList saleList);

	/***
	 * 根据传入的销售单号查询id
	 * @param saleNumber
	 * @return
	 */
	public List<SaleList> findSaleListId(String saleNumber);

    SaleList findById(Integer id);
}
