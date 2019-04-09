package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Brand;

/**
 * 商标Repository接口
 * @author Administrator
 *
 */
public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand>{
	/**
	 * 根据仓库名查询商标信息
	 * @param name
	 * @return
	 */
	@Query(value="select * from t_brand where name=?1",nativeQuery=true)
	public Brand findByBrandName(String name);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	@Query(value = "select * from t_brand where name like ?1", nativeQuery=true)
	public List<Brand> findByName(String string);
}
