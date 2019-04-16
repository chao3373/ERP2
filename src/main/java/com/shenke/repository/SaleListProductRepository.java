package com.shenke.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.shenke.entity.JitaiProductionAllot;
import com.shenke.entity.SaleListProduct;

@Transactional
public interface SaleListProductRepository
		extends JpaRepository<SaleListProduct, Integer>, JpaSpecificationExecutor<SaleListProduct> {

	/**
	 * 根据销售单id查询订单中的所有未审核的商品信息
	 * 
	 * @param saleListId
	 * @return
	 */
	@Query(value = "SELECT * FROM t_sale_list_product WHERE sale_list_id = ?1 ORDER BY state", nativeQuery = true)
	public List<SaleListProduct> listBySaleListId(Integer saleListId);

	/**
	 * 根据销售单id删除所有商品信息0
	 * 
	 * @param id
	 */
	@Modifying
	@Query(value = "delete from t_sale_list_product where sale_list_id = ?1", nativeQuery = true)
	public void deleteBySaleListId(Integer id);

	/**
	 * 订单审核通过
	 * 
	 * @param id
	 */
	@Modifying
	@Query(value = "update t_sale_list_product set state = '审核通过' where id = ?1", nativeQuery = true)
	public void passTheAudit(int id);

	/**
	 * 订单审核失败
	 * 
	 * @param id
	 */
	@Modifying
	@DeleteMapping
	@Query(value = "update t_sale_list_product set state = ?2 where id = ?1", nativeQuery = true)
	public void auditFailure(int id, String cause);

	/**
	 * 根据订单审核状态查询所有订单信息
	 * 
	 * @param state
	 */
	@Query(value = "select * from t_sale_list_product where state like %?1%", nativeQuery = true)
	public List<SaleListProduct> listProductByState(String state);

	/**
	 * 根据订单审核状态和销售单id查询所有订单信息
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	@Query(value = "select * from t_sale_list_product where state like %?2% and sale_list_id = ?1", nativeQuery = true)
	public List<SaleListProduct> listProductByStateAndId(Integer id, String state);

	/**
	 * 查询所有审核通过的商品信息
	 * 
	 * @return
	 */
	@Query(value = "select * from t_sale_list_product where state like '%审核通过%'", nativeQuery = true)
	public List<SaleListProduct> listProductSucceed();

	/**
	 * 下拉框模糊查询所有幅宽信息
	 * 
	 * @param q
	 * @return
	 */
	@Query(value = "select * from t_sale_list_product where model like ?1", nativeQuery = true)
	public List<SaleListProduct> breadthList(String q);

	/**
	 * 根据订单商品信息修改订单商品状态
	 * 
	 * @param name
	 * @param id
	 */
	@Modifying
	@Query(value = "UPDATE t_sale_list_product SET state = ?1 where id = ?2", nativeQuery = true)
	public void updateState(String name, Integer id);

	/**
	 * 根据机台id和通知单号查询所有销售商品信息
	 * 
	 * @param jitai
	 * @param parseLong
	 * @return
	 */
	@Query(value = "SELECT * FROM t_sale_list_product WHERE id IN (SELECT sale_list_product_id FROM t_jitai_production_allot WHERE jitai_id = ?1 AND inform_number = ?2)", nativeQuery = true)
	public List<SaleListProduct> selectAllByInformAndJitaiId(Integer jitai, Long parseLong);

	/**
	 * 根据id修改实际重量
	 * @param weight
	 * @param saleListProductId
	 */
	@Modifying
	@Query(value = "UPDATE t_sale_list_product SET realityweight = ?1 where id = ?2", nativeQuery = true)
	public void updaterRealityWeightById(Double weight, Integer saleListProductId);


}
