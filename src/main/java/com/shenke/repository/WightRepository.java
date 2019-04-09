package com.shenke.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Wight;

/**
 * 重量设置Repository
 * 
 * @author Administrator
 *
 */
public interface WightRepository extends JpaRepository<Wight, Integer>, JpaSpecificationExecutor<Wight> {
	/**
	 * 根据仓库名查询 重量信息
	 * 
	 * @param name
	 * @return
	 */
	@Query(value = "select * from t_wight where name=?1", nativeQuery = true)
	public Wight findByWightName(String name);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	@Query(value="select * from t_wight where name like ?1", nativeQuery = true)
	public List<Wight> findByName(String string);
}
