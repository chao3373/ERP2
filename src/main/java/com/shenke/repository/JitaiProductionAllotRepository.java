package com.shenke.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.shenke.entity.JiTai;
import com.shenke.entity.JitaiProductionAllot;
import com.shenke.entity.SaleListProduct;

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

	/**
	 * 根据分配的机台id查询所有分配到该机台的生产通知单
	 * 
	 * @param jitaiId
	 * @return
	 */
	@Query(value = "select * from t_jitai_production_allot where jitai_id = ?1", nativeQuery = true)
	public List<JitaiProductionAllot> findByJitaiId(Integer jitaiId);

	/**
	 * 根据机台id查询该机台下的所有通知单号
	 * 
	 * @param jitaiId
	 * @return
	 */
	@Query(value = "select inform_number from t_jitai_production_allot where jitai_id = ?1", nativeQuery = true)
	public Set<Integer> selectAllInformByJitaiId(Integer jitaiId);

	/**
	 * 根据销售商品id查询生产通知单
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "SELECT * FROM t_jitai_production_allot WHERE sale_list_product_id =?1 and id not in(select id from t_jitai_production_allot where issue_state like '%完成%')", nativeQuery = true)
	public List<JitaiProductionAllot> selectBySaleListProductId(Integer id);

	/**
	 * 根据id修改生产通知单状态
	 * @param string
	 * @param jitaiProductionAllotId
	 */
	@Modifying
	@Query(value = "UPDATE t_jitai_production_allot SET issue_state = ?1 WHERE id = ?2", nativeQuery = true)
	public void updateStateById(String string, Integer jitaiProductionAllotId);

}
