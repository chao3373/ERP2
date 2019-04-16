package com.shenke.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.ProductionProcess;

/**
 * 生产加工单Repository
 * 
 * @author Administrator
 *
 */
public interface ProductionProcessRepository
		extends JpaSpecificationExecutor<ProductionProcess>, JpaRepository<ProductionProcess, Integer> {

	/**
	 * 根据机台id查询生产加工单
	 * 
	 * @param jitaiId
	 * @return
	 */
	@Query(value = "SELECT 	* FROM t_production_process WHERE ji_tai_id = ?1", nativeQuery = true)
	public List<ProductionProcess> selectByJitaiId(Integer jitaiId);

	/**
	 * 根据生产状态查询生产加工单
	 * 
	 * @param issueState
	 * @return
	 */
	@Query(value = "select * from t_production_process where issue_state like ?1", nativeQuery = true)
	public List<ProductionProcess> selectByIssueState(String issueState);

	/**
	 * 查询所有未完成的生产加工单
	 * 
	 * @return
	 */
	@Query(value = "SELECT * FROM t_production_process WHERE id NOT IN (SELECT id FROM t_production_process WHERE state LIKE '%完成%' OR issue_state LIKE '%未下发%') AND ji_tai_id = ?1", nativeQuery = true)
	public List<ProductionProcess> selectNoAccomplish(Integer jitaiId);

	/**
	 * 修改生产加工单状态
	 * 
	 * @param string
	 * @param id
	 */
	@Modifying
	@Query(value = "UPDATE t_production_process SET state = ?1 WHERE id = ?2", nativeQuery = true)
	public void updateState(String string, Integer id);

	/**
	 * 根据id获取完成数量
	 * 
	 * @param producionProcessId
	 */
	@Query(value = "SELECT accomplish_number FROM t_production_process WHERE id =?1", nativeQuery = true)
	public Integer findAccomplishNumberById(Integer producionProcessId);

	
	/**
	 * 根据id修改完成数
	 * @param i
	 * @param producionProcessId
	 */
	@Modifying
	@Query(value = "UPDATE t_production_process SET accomplish_number = ?1 WHERE id = ?2", nativeQuery = true)
	public void updateAccomplishNumberById(Integer i, Integer producionProcessId);

	/**
	 * 根据SaleListProductId修改生产通知单下发状态
	 * @param string
	 * @param id
	 */
	@Modifying
	@Query(value = "UPDATE t_production_process SET issue_state = ?1 WHERE sale_list_product_id = ?2", nativeQuery = true)
	public void updateIssueStateBySaleListProductId(String string, Integer id);
	
	/**
	 * 根据SaleListProductId修改生产通知单状态
	 * @param string
	 * @param id
	 */
	@Modifying
	@Query(value = "UPDATE t_production_process SET state = ?1 WHERE sale_list_product_id = ?2", nativeQuery = true)
	public void updateStateBySaleListProductId(String string, Integer id);

}
