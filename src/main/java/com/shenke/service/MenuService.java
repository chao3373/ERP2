package com.shenke.service;

import java.util.List;

import com.shenke.entity.Menu;

/**
 * 权限菜单接口
 * @author Administrator
 *
 */
public interface MenuService {

	/**
	 * 根据父节点跟角色id查询菜单
	 * @param parentId
	 * @param roleId
	 * @return
	 */
	public List<Menu> findByParentIdAndRoleId(Integer parentId, Integer roleId);

	/**
	 * 根据角色id获取菜单集合
	 * @param roleId
	 * @return
	 */
	public List<Menu> findByRoleId(int roleId);
	
	/**
	 * 根据父节点查找所有子节点
	 * @param parentId
	 * @return
	 */
	public List<Menu> findByParentId(int id);

	/**
	 * 根据id查询菜单
	 * @param parseInt
	 * @return
	 */
	public Menu findById(int id);
	
}
