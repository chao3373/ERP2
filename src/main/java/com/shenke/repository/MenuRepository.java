package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shenke.entity.Menu;

/**
 * 权限菜单Repository接口
 * @author Administrator
 *
 */
public interface MenuRepository extends JpaRepository<Menu, Integer>{
	
	@Query(value="SELECT m.* FROM t_role r, t_role_menu rm, t_menu m WHERE rm.role_id = r.id AND rm.menu_id = m.id AND r.id = ?1", nativeQuery = true)
	public List<Menu> findByRoleId(Integer id);

	
	/**
	 * 根据父节点的id跟角色id查询菜单
	 * @param parentId
	 * @param roleId
	 * @return
	 */
	@Query(value="select * from t_menu where p_id = ?1 and id in(select menu_id from t_role_menu where role_id = ?2)", nativeQuery=true)
	public List<Menu> findByParentIdAndRoleId(Integer parentId, Integer roleId);


	/**
	 * 根据父节点查找所有子节点
	 * @param parentId
	 * @return
	 */
	@Query(value="select * from t_menu where p_id=?1",nativeQuery=true)
	public List<Menu> findByParentId(int parentId);
	
}
