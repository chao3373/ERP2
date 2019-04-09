package com.shenke.service;

import com.shenke.entity.RoleMenu;

public interface RoleMenuService {

	/**
	 * 根据角色id删除角色
	 * @param id
	 */
	public void deleteByRoleId(Integer id);

	/**
	 * 保存用户菜单关联信息
	 * @param roleMenu
	 */
	public void save(RoleMenu roleMenu);

}
