package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shenke.entity.Clerk;
/**
 * 员工Repository接口
 * @author Administrator
 *
 */
public interface ClerkRepository extends JpaRepository<Clerk, Integer>, JpaSpecificationExecutor<Clerk>{

	/**
	 * 根据dep_id查询所有员工信息
	 * @param id
	 * @return
	 * 
	 */
	@Query(value="select * from t_clerk where dep_id=?1", nativeQuery=true)
	public List<Clerk> findByDepId(Integer id);

	/**
	 * 下拉框模糊查询销售部人员
	 * @param string
	 * @return
	 * 
	 */
	@Query(value="SELECT * FROM t_clerk WHERE NAME LIKE ?1", nativeQuery=true)
	public List<Clerk> findByName(String string);
	
}
