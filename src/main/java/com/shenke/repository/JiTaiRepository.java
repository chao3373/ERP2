package com.shenke.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.JiTai;

/**
 * 机台Repository
 * @author Administrator
 *
 */
public interface JiTaiRepository extends JpaRepository<JiTai, Integer>, JpaSpecificationExecutor<JiTai>{
	/**
	 * 根据仓库名查询班组信息
	 * @param name
	 * @return
	 */
	@Query(value="select * from t_jitai where name=?1",nativeQuery=true)
	public JiTai findByJiTaiName(String name);

	/**
	 * 下拉框模糊查询机台信息
	 * @param string
	 * @return
	 */
	@Query(value = "select * from t_jitai where name like ?1", nativeQuery = true)
	public List<JiTai> findByName(String string);
}
