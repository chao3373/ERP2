package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Letter;

/**
 * 印字设置Repository
 * 
 * @author Administrator
 *
 */
public interface LetterRepository extends JpaRepository<Letter, Integer>, JpaSpecificationExecutor<Letter> {
	/**
	 * 根据仓库名查询印字信息
	 * 
	 * @param name
	 * @return
	 */
	@Query(value = "select * from t_letter where name=?1", nativeQuery = true)
	public Letter findByLetterName(String name);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	@Query(value = "select * from t_letter where name like ?1", nativeQuery = true)
	public List<Letter> findByName(String string);
}
