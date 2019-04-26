package com.shenke.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Client;

/**
 * 客户Repository
 * 
 * @author Administrator
 *
 */
public interface ClientRepository extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {
	/**
	 * 根据dep_id查询所有员工信息
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "select * from t_client where type_id=?1", nativeQuery = true)
	public List<Client> findByClientTypeId(Integer id);

	/**
	 * 根据名称模糊查询
	 * @param string
	 * @return
	 */
	@Query(value="select * from t_client where name like ?1", nativeQuery = true)
	public List<Client> findByName(String string);

	/**
	 * 根据名称查询
	* @Description:
	* @Param:
	* @return:
	* @Author: Andy
	* @Date:
	*/
	@Query(value="select * from t_client where name = ?1", nativeQuery = true)
	public Client findByNam(String clname);
}
