package com.shenke.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.ClientType;

public interface ClientTypeRepository extends JpaRepository<ClientType, Integer>, JpaSpecificationExecutor<ClientType> {
	/**
	 * 根据父节点查询所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	@Query(value = "select * from t_clienttype where p_id=?1", nativeQuery = true)
	public List<ClientType> findByParentId(Integer parentId);
}
