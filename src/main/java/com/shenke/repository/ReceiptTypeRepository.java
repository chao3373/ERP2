package com.shenke.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.ReceiptType;

/**
 * 收付款方式Type Repository
 * 
 * @author Administrator
 *
 */
public interface ReceiptTypeRepository
		extends JpaRepository<ReceiptType, Integer>, JpaSpecificationExecutor<ReceiptType> {
	/**
	 * 根据父节点查询所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	@Query(value = "select * from t_receipttype where p_id=?1", nativeQuery = true)
	public List<ReceiptType> findByParentId(Integer parentId);
}
