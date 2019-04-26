package com.shenke.repository;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shenke.entity.Brand;
import com.shenke.entity.Location;
import com.shenke.entity.Pandianji;

public interface LocationRepository extends JpaRepository<Location, Integer>, JpaSpecificationExecutor<Location>{

	/**
	 * 根据名称查询仓位
	 * 
	 * @param name
	 * @return
	 */
	@Query(value = "select * from t_location where name =?1",nativeQuery = true)
	public Location findByLocationName(String name);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	@Query(value = "select * from t_location where name like ?1", nativeQuery=true)
	public List<Location> findByName(String string);





}
