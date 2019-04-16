package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Group;

/**
 * 班组设置Repository
 * @author Administrator
 *
 */
public interface GroupRepository extends JpaRepository<Group, Integer>, JpaSpecificationExecutor<Group>{
	/**
	 * 根据仓库名查询班组信息
	 * @param name
	 * @return
	 */
	@Query(value="select * from t_group where name=?1",nativeQuery=true)
	public Group findByGrouptName(String name);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	@Query(value = "select * from t_group where name like ?1", nativeQuery = true)
	public List<Group> findByName(String string);
}
