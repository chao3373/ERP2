package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shenke.entity.Pandianji;
import com.shenke.entity.Wight;

/**
 * 盘点机Repository接口
 * @author shao
 *
 */

public interface PandianjiRepository extends JpaRepository<Pandianji, Integer>, JpaSpecificationExecutor<Pandianji> {
	/**
	 * 根据名称查找盘点机实体
	 * 
	 */
	@Query(value = "select * from t_pandianji where name =?1",nativeQuery = true)
	public Pandianji findByPandianjiName(String name);
	
	/**
	 * 下拉框模糊查询
	 * 
	 */
	
	@Query (value="select * from t_pandianji where pid like ?1",nativeQuery =true)
	public List<Pandianji> findByPid(String string);

	//根据序列号查询盘点机名称
	@Query(value = "select name from t_pandianji where pid = ?1", nativeQuery = true)
    String findbyPid(String pid);
}
