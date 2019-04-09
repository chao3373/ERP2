package com.shenke.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shenke.entity.Menu;
import com.shenke.repository.MenuRepository;
import com.shenke.service.MenuService;

/**
 * 权限菜单实现类
 * @author Administrator
 *
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Resource
	private MenuRepository menuRepository; 
	
	@Override
	public List<Menu> findByParentIdAndRoleId(Integer parentId, Integer roleId) {
		return menuRepository.findByParentIdAndRoleId(parentId, roleId);
	}

	@Override
	public List<Menu> findByRoleId(int roleId) {
		return menuRepository.findByRoleId(roleId);
	}

	@Override
	public List<Menu> findByParentId(int parentId) {
		return menuRepository.findByParentId(parentId);
	}

	@Override
	public Menu findById(int id) {
		return menuRepository.findOne(id);
	}
	
}
