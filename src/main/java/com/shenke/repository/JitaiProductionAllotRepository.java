package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.shenke.entity.JiTai;
import com.shenke.entity.JitaiProductionAllot;

public interface JitaiProductionAllotRepository
		extends JpaRepository<JitaiProductionAllot, Integer>, JpaSpecificationExecutor<JitaiProductionAllot> {

	/**
	 * 
	 * 获取最大的通知单号
	 * 
	 * @return
	 */
	@Query(value = "SELECT MAX(inform_number) FROM t_jitai_production_allot", nativeQuery = true)
	public Long findMaxInfornNumber();

	/**
	 * 下发机台
	 * 
	 * @param parseInt
	 */
	@Modifying
	@Query(value = "update t_jitai_production_allot set issue_state = ?1 where id = ?2", nativeQuery = true)
	public void issue(String name, int parseInt);

	/**
	 * 查询所有属于该销售订单的生产通知单
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "select * from t_jitai_production_allot where sale_list_product_id = ?1", nativeQuery = true)
	public List<JitaiProductionAllot> findBySaleListProductId(Integer id);

	/**
	 * 修改所有属于该销售订单的生产通知单的数量
	 * 
	 * @param countSaleListProduct
	 * @param id
	 */
	@Modifying
	@Query(value = "update t_jitai_production_allot set num = ?1 where sale_list_product_id = ?2", nativeQuery = true)
	public void updateNum(Integer countSaleListProduct, int id);

	/**
	 * 分组查询所有生产通知单
	 * 
	 * @return
	 */
	@Query(value = "SELECT * FROM t_jitai_production_allot GROUP BY sale_list_product_id", nativeQuery = true)
	public List<JitaiProductionAllot> listGroubBy();

	/**
	 * 查询属于该销售商品信息的所有生产通知单的数量
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "select count(*) from t_jitai_production_allot where sale_list_product_id = ?1", nativeQuery = true)
	public Integer countBySaleListProductId(Integer id);

	/**
	 * 根据通知单号修改分配机台信息
	 * 
	 * @param parseInt
	 * @param jiTai2
	 */
	@Modifying
	@Query(value = "update t_jitai_production_allot set jitai_id  = ?2 where inform_number = ?1", nativeQuery = true)
	public void updateJitai(int parseInt, Integer jiTai2);

	/**
	 * 根据通知单号修改分配状态
	 * 
	 * @param string
	 * @param parseInt
	 */
	@Modifying
	@Query(value = "update t_jitai_production_allot set allot_state  = ?1 where inform_number = ?2", nativeQuery = true)
	public void updateAllotState(String string, int parseInt);

	/**
	 * 根据通知单号查询生产通知单
	 * 
	 * @param parseInt
	 */
	@Query(value = "select * from t_jitai_production_allot where inform_number = ?1", nativeQuery = true)
	public List<JitaiProductionAllot> findByImformNubers(int parseInt);

}
