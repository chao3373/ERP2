package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.EntrepotType;

/**
 * 仓位Type Repository
 * 
 * @author Administrator
 *
 */
public interface EntrepotTypeRepository
		extends JpaRepository<EntrepotType, Integer>, JpaSpecificationExecutor<EntrepotType> {
	/**
	 * 根据父节点查询所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	@Query(value = "select * from t_entrepottype where p_id=?1", nativeQuery = true)
	public List<EntrepotType> findByParentId(Integer parentId);
}
