package com.shenke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shenke.entity.User;

/**
 * 用户Repository接口
 * @author Administrator
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{
	
	/**
	 * 根据用户名查找用户实体
	 * @param userName
	 * @return
	 */
	@Query(value="select * from t_user where user_name=?1",nativeQuery=true)
	public User findByUserName(String userName);

	/**
	 * 根据用户名和密码查询用户
	 * @param userName
	 * @param pwd
	 * @return
	 */
	@Query(value = "select * from t_user where user_name = ?1 and password = ?2", nativeQuery = true)
	public User findByuserNameAndPwd(String userName, String pwd);
}
