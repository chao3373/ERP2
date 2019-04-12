package com.shenke.service;

import java.util.List;

import com.shenke.entity.JiTai;
import com.shenke.entity.JitaiProductionAllot;

/**
 * 机台生产分配Service
 * @author Administrator
 *
 */
public interface JitaiProductionAllotService {

	//保存机台生产分配信息
	public void save(JitaiProductionAllot jitaiProductionAllot);

	/**
	 * 获取最大的通知单号
	 * 
	 */
	public Long findMaxInfornNumber();

	/**
	 * 查询所有生产通知单
	 * 
	 * @return
	 */
	public List<JitaiProductionAllot> list();

	/**
	 * 根据条件查询生产通知单信息
	 * @param allorTime
	 * @param jitai
	 * @return
	 */
	public List<JitaiProductionAllot> screen(String allorTime, Integer jitai);

	/**
	 * 下发机台
	 * @param parseInt
	 */
	public void issue(String name, int parseInt);

	/**
	 * 查询属于该订单商品的生产通知单的数量
	 * @param id
	 * @return
	 */
	public Integer countBySaleListProductId(Integer id);

	/**
	 * 
	 * 查询属于该订单商品的所有生产通知单
	 * @param id
	 * @return
	 */
	public List<JitaiProductionAllot> findBySaleListProductId(Integer id);

	/**
	 * 更新属于该订单商品的所有生产通知单的数量
	 * @param countSaleListProduct
	 * @param id
	 */
	public void updateNum(Integer countSaleListProduct, int id);

	/**
	 * 分组查询所有生产通知单
	 * @return
	 */
	public List<JitaiProductionAllot> listGroubBy();

	/**
	 * 根据通知单号修改分配机台信息
	 * @param parseInt
	 * @param jiTai2
	 */
	public void updateJitai(int parseInt, Integer jiTai2);

	/**
	 * 根据通知单号查询生产通知单
	 * @param string
	 * @return
	 */
	public JitaiProductionAllot findOne(Integer informNumber);

	/**
	 * 根据通知单号获取该单号中的所有通知单
	 * @param parseInt
	 * @return
	 */
	public List<JitaiProductionAllot> findByImformNubers(int parseInt);

	/**
	 * 根据id获取生产通知单
	 * @param string
	 */
	public JitaiProductionAllot findById(Integer id);


}
