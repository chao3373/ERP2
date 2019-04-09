package com.shenke.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Entrepot;

public interface EntrepotRepository extends JpaRepository<Entrepot, Integer>, JpaSpecificationExecutor<Entrepot> {
	/**
	 * 根据type_id查询所有仓位信息
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "select * from t_entrepot where type_id=?1", nativeQuery = true)
	public List<Entrepot> findByEntrepotTypeId(Integer id);
}
