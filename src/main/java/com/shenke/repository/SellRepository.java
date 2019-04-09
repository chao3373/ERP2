package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shenke.entity.Client;
import com.shenke.entity.Sell;

/**
 * 销售方式Repository
 * @author Administrator
 *
 */
public interface SellRepository extends JpaRepository<Sell, Integer>, JpaSpecificationExecutor<Sell>{
	/**
	 * 根据销售方式查询仓库
	 * @param name
	 * @return
	 */
	@Query(value="select * from t_sell where method=?1",nativeQuery=true)
	public Sell findBySellMethod(String method);

	/**下拉框模糊查询
	 * @return
	 */
	@Query(value="select * from t_sell where method like ?1",nativeQuery=true)
	public List<Sell> findByName(String method);
}
