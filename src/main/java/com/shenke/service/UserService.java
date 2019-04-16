package com.shenke.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.shenke.entity.User;

/**
 * 用户Service
 * @author Administrator
 *
 */
public interface UserService {

	
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName);

	/**
	 * 根据条件分页查询用户信息
	 * @param user
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<User> list(User user, Integer page, Integer rows, Direction asc, String...string);

	/**
	 * 总记录数
	 * @param user
	 * @return
	 */
	public Long getCount(User user);

	/**
	 * 根据用户id查询用户
	 * @param userId
	 * @return
	 */
	public User findById(Integer userId);

	/**
	 * 添加或修改用户信息
	 * @param user
	 */
	public void save(User user);

	/**
	 * 根据id删除用户
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 根据用户名和密码判断是否存在该用户
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public Object findNamePsw(String name, String psw);
	
}
