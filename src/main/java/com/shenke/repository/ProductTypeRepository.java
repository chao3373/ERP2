package com.shenke.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.ProductType;

/**
 * 产品及原料Repository
 * 
 * @author Administrator
 *
 */
public interface ProductTypeRepository
		extends JpaRepository<ProductType, Integer>, JpaSpecificationExecutor<ProductType> {
	/**
	 * 根据父节点查询所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	@Query(value = "select * from t_producttype where p_id=?1", nativeQuery = true)
	public List<ProductType> findByParentId(Integer parentId);
}
