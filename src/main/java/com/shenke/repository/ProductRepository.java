package com.shenke.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Product;

/**
 * 产品及原料Repository
 * 
 * @author Administrator
 *
 */
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
	/**
	 * 根据dep_id查询所有员工信息
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "select * from t_product where type_id=?1", nativeQuery = true)
	public List<Product> findByProductTypeId(Integer id);

	/**
	 * 下拉框模糊查询
	 * 
	 * @param string
	 * @return
	 */
	@Query(value="select * from t_product where type_id=2 and name like ?1", nativeQuery=true)
	public List<Product> findByName(String string);

	/**
	 * 根据姓名查询
	* @Description:
	* @Param:
	* @return:
	* @Author: Andy
	* @Date:
	*/
	@Query(value = "select * from t_product where name = ?1", nativeQuery = true)
    public Product findByNam(String name);
}
