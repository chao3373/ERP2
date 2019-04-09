package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shenke.entity.Role;

/**
 * Relo的Repository接口
 * @author Administrator
 *
 */
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role>{

	/**
	 * 根据用户id查询所拥有的角色
	 * @param uid
	 * @return
	 */
	@Query(value="select r.* from t_user u, t_role r, t_user_role ur where ur.user_id = u.id and ur.role_id = r.id and u.id = ?1", nativeQuery=true)
	public List<Role> findByUserId(Integer uid);

	/**
	 * 根据角色名查找角色实体
	 * @param roleName
	 * @return
	 */
	@Query(value="select * from t_role where name=?1",nativeQuery=true)
	public Role findByRoleName(String roleName);

	
}
