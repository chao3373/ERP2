package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Pack;

/**
 * 包装设置Repository
 * @author Administrator
 *
 */
public interface PackRepository extends JpaRepository<Pack, Integer>, JpaSpecificationExecutor<Pack>{
	/**
	 * 根据仓库名查询包装信息
	 * @param name
	 * @return
	 */
	@Query(value="select * from t_pack where name=?1",nativeQuery=true)
	public Pack findByPackName(String name);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	@Query(value = "select * from t_pack where name like ?1", nativeQuery = true)
	public List<Pack> findByName(String string);
}
