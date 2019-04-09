package com.shenke.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.shenke.entity.Patu;

/**
 * 纸管设置Service
 * @author Administrator
 *
 */
public interface PatuService {
	/**
	 * 分页查询纸管信息
	 * 
	 * @param entrepot
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<Patu> list(Patu patu, Integer page, Integer rows, Direction asc, String... properties);

	/**
	 * 获取总记录数
	 * 
	 * @param entrepot
	 * @return
	 */
	public Long getCount(Patu patu);

	/**
	 * 根据仓库名称纸管信息
	 * 
	 * @param name
	 * @return
	 */
	public Patu findByPatuName(String name);

	/**
	 * 添加或者修改纸管信息
	 * 
	 * @param entrepot
	 */
	public void save(Patu patu);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Patu findById(Integer id);

	/**
	 * 根据id删除纸管信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	public List<Patu> findByName(String string);
}
