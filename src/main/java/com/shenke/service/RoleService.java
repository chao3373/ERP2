package com.shenke.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.shenke.entity.Role;

/**
 * 角色service
 * @author Administrator
 *
 */
public interface RoleService {

	/**
	 * 根据用户id查询所拥有的角色
	 * @param uid
	 * @return
	 */
	public List<Role> findByUserId(Integer uid);

	/**
	 * 根据角色id查询角色
	 * @param roleId
	 * @return
	 */
	public Role findById(Integer roleId);

	/**
	 * 查询所有角色信息
	 * @return
	 */
	public List<Role> listAll();

	/**
	 * 根据条件分页查询所有角色
	 * @param role
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<Role> list(Role role, Integer page, Integer rows, Direction asc, String string);

	/**
	 * 获取总记录数
	 * @param role
	 * @return
	 */
	public Long getCount(Role role);

	/**
	 * 根据角色名查询角色
	 * @param name
	 * @return
	 */
	public Role findByRoleName(String name);

	/**
	 * 添加或修改角色信息
	 * @param role
	 */
	public void save(Role role);

	/**
	 * 根据id删除角色
	 * @param id
	 */
	public void delete(Integer id);

}
