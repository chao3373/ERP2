package com.shenke.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Receipt;

/**
 * 收付款方式Repository
 * 
 * @author Administrator
 *
 */
public interface ReceiptRepository extends JpaRepository<Receipt, Integer>, JpaSpecificationExecutor<Receipt> {
	/**
	 * 根据dep_id查询所有员工信息
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "select * from t_receipt where type_id=?1", nativeQuery = true)
	public List<Receipt> findByDepId(Integer id);
}
