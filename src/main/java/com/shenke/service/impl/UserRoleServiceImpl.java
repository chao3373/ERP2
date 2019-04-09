package com.shenke.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenke.entity.UserRole;
import com.shenke.repository.UserRoleRepository;
import com.shenke.service.UserRoleService;


/**
 * 用户关联角色的实现类
 * @author Administrator
 *
 */
@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService{
	
	@Resource
	private UserRoleRepository userRoleRepository;

	@Override
	public void deleteByUserId(Integer userId) {
		userRoleRepository.deleteByUserId(userId);
	}

	@Override
	public void save(UserRole userRole) {
		userRoleRepository.save(userRole);
	}

	@Override
	public void deleteByRoleId(Integer id) {
		userRoleRepository.deleteByRoleId(id);
	}

}
