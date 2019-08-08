package com.shenke.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.shenke.entity.Pandianji;

/**
 * 盘点机Service
 * @author shao
 *
 */
public interface PandianjiService {

	/**
	 * 根据盘点机名称查询盘点机
	 * 
	 */
	public Pandianji findByPandianjiName(String name);
	
	
	/**
	 *根据条件分页查询用户信息 
	 * 
	 */
	public List<Pandianji> list(Pandianji pandianji, Integer page, Integer rows, Direction asc, String... string);
	
	/**
	 * 总记录数
	 * @param pandianji
	 */
	public Long getCount(Pandianji pandianji);
	
	/**
	 * 根据用户id查询用户
	 * 
	 */
	public Pandianji findById(Integer pandianjiId);
	
	/**
	 * 添加或修改用户信息
	 * @param pandianji
	 */
	public void save(Pandianji pandianji);
	
	/**
	 * 根据id删除用户
	 * @param id
	 */
	public void delete(Integer id);
	
	
	
	/**
	 * 下拉模糊查询
	 * 
	 */
	public List<Pandianji> findByPid(String string);


    String findbyPid(String pid);
}

















