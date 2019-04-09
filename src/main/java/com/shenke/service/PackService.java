package com.shenke.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.shenke.entity.Pack;

/**
 * 包装设置Service接口
 * @author Administrator
 *
 */
public interface PackService {
	/**
	 * 分页查询包装信息
	 * 
	 * @param entrepot
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<Pack> list(Pack pack, Integer page, Integer rows, Direction asc, String... properties);

	/**
	 * 获取总记录数
	 * 
	 * @param entrepot
	 * @return
	 */
	public Long getCount(Pack Pack);

	/**
	 * 根据仓库名称包装信息
	 * 
	 * @param name
	 * @return
	 */
	public Pack findByPackName(String name);

	/**
	 * 添加或者修改包装信息
	 * 
	 * @param entrepot
	 */
	public void save(Pack pack);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Pack findById(Integer id);

	/**
	 * 根据id删除包装信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	public List<Pack> findByName(String string);
}
