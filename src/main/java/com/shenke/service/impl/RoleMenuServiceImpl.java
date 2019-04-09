package com.shenke.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenke.entity.RoleMenu;
import com.shenke.repository.RoleMenuRepository;
import com.shenke.service.RoleMenuService;

@Service("roleMenuService")
@Transactional
public class RoleMenuServiceImpl implements RoleMenuService{

	@Resource
	private RoleMenuRepository roleMenuServiceRepository;
	
	@Override
	public void deleteByRoleId(Integer id) {
		roleMenuServiceRepository.deleteByRoleId(id);
	}

	@Override
	public void save(RoleMenu roleMenu) {
		roleMenuServiceRepository.save(roleMenu);
	}

}
