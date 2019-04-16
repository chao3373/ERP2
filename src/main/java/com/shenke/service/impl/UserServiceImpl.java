package com.shenke.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenke.entity.User;
import com.shenke.repository.UserRepository;
import com.shenke.service.UserService;
import com.shenke.util.StringUtil;

/**
 * 用户Service实现类
 * 
 * @author Administrator
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;

	/*
	 * 根据用户名查询用户
	 * 
	 * @see com.shenke.service.UserService#findByUserName(java.lang.String)
	 */
	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public List<User> list(User user, Integer page, Integer pageSize, Direction direction, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<User> pageUser = userRepository.findAll(new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (user != null) {
					if (StringUtil.isNotEmpty(user.getUserName())) {
						predicate.getExpressions().add(cb.like(root.get("userName"), "%" + user.getUserName() + "%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}

	@Override
	public Long getCount(User user) {
		Long count = userRepository.count(new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (user != null) {
					if (StringUtil.isNotEmpty(user.getUserName())) {
						predicate.getExpressions().add(cb.like(root.get("userName"), "%" + user.getUserName() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}
		});
		return count;
	}

	@Override
	public User findById(Integer userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(Integer id) {
		userRepository.delete(id);
	}

	@Override
	public Object findNamePsw(String name, String psw) {
		return userRepository.findByuserNameAndPwd(name, psw);
	}

}
