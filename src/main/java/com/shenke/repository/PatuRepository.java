package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Patu;

/**
 * 纸管设置Repository
 * 
 * @author Administrator
 *
 */
public interface PatuRepository extends JpaRepository<Patu, Integer>, JpaSpecificationExecutor<Patu> {
	/**
	 * 根据仓库名查询纸管信息
	 * 
	 * @param name
	 * @return
	 */
	@Query(value = "select * from t_patu where name=?1", nativeQuery = true)
	public Patu findByPatuName(String name);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	@Query(value="select * from t_patu where name like ?1",nativeQuery = true)
	public List<Patu> findByName(String string);
}
