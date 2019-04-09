package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import com.shenke.entity.Require;

/**
 * 要求设置Service
 * 
 * @author Administrator
 *
 */
public interface RequireService {
	/**
	 * 分页查询要求信息
	 * 
	 * @param entrepot
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<Require> list(Require Require, Integer page, Integer rows, Direction asc, String... properties);

	/**
	 * 获取总记录数
	 * 
	 * @param entrepot
	 * @return
	 */
	public Long getCount(Require Require);

	/**
	 * 根据仓库名称要求信息
	 * 
	 * @param name
	 * @return
	 */
	public Require findByRequireName(String name);

	/**
	 * 添加或者修改要求信息
	 * 
	 * @param entrepot
	 */
	public void save(Require Require);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Require findById(Integer id);

	/**
	 * 根据id删除要求信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	public List<Require> findByName(String string);
	
	
}
