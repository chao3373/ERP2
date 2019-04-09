package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Require;

/**
 * 要求设置Repository
 * 
 * @author Administrator
 *
 */
public interface RequireRepository extends JpaRepository<Require, Integer>, JpaSpecificationExecutor<Require> {
	/**
	 * 根据仓库名查询要求信息
	 * 
	 * @param name
	 * @return
	 */
	@Query(value = "select * from t_require where name=?1", nativeQuery = true)
	public Require findByRequireName(String name);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	@Query(value = "select * from t_require where name like ?1", nativeQuery = true)
	public List<Require> findByName(String string);
}
