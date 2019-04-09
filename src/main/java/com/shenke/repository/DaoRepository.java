package com.shenke.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Dao;

/**
 * 剖刀设置Repository
 * @author Administrator
 *
 */
public interface DaoRepository extends JpaRepository<Dao, Integer>, JpaSpecificationExecutor<Dao>{
	
	/**
	 * 根据仓库名查询剖刀信息
	 * @param name
	 * @return
	 */
	@Query(value="select * from t_dao where name=?1",nativeQuery=true)
	public Dao findByDaoName(String name);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	@Query(value = "select * from t_dao where name like ?1", nativeQuery = true)
	public List<Dao> findByName(String string);
}
