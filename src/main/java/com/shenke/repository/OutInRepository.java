package com.shenke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.OutIn;

/**
 * 出入库信息Repository
 * @author Administrator
 *
 */
public interface OutInRepository extends JpaRepository<OutIn, Integer>, JpaSpecificationExecutor<OutIn>{
	/**
	 * 根据出入库名查询出入库信息
	 * @param name
	 * @return
	 */
	@Query(value="select * from t_outin where name=?1",nativeQuery=true)
	public OutIn findByOutInName(String name);
}
