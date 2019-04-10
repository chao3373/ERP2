package com.shenke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shenke.entity.JitaiProductionAllot;

public interface JitaiProductionAllotRepository extends JpaRepository<JitaiProductionAllot, Integer>, JpaSpecificationExecutor<JitaiProductionAllot>{

	/**
	 * 
	 * 获取最大的通知单号
	 * @return
	 */
	@Query(value = "SELECT MAX(inform_number) FROM t_jitai_production_allot", nativeQuery = true)
	public Long findMaxInfornNumber();
	
}
