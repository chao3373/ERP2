package com.shenke.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.shenke.entity.Brand;
import com.shenke.entity.Location;

/**
 * 
 * 仓位Service接口
 * @author shao
 *
 */
public interface LocationService {

	
	/**
	 * 分页查询仓位信息
	 * 
	 */
	public List<Location> list(Location location, Integer page, Integer rows, Direction asc, String... properties);

	/**
	 * 获取总记录数
	 * 
	 * @param entrepot
	 * @return
	 */
	public Long getCount(Location location);

	/**
	 * 根据名称查询仓位信息
	 * 
	 * @param name
	 * @return
	 */
	public Location findByLocationName(String name);

	/**
	 * 添加或者修改仓位信息
	 * 
	 * @param entrepot
	 */
	public void save(Location location);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Location findById(Integer id);

	/**
	 * 根据id删除仓位信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	public List<Location> findByName(String string);
}
