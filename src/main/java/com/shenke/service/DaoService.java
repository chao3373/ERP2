package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import com.shenke.entity.Dao;

/**
 * 剖刀Service
 * @author Administrator
 *
 */
public interface DaoService {
	/**
	 * 分页查询剖刀信息
	 * 
	 * @param entrepot
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<Dao> list(Dao dao, Integer page, Integer rows, Direction asc, String... properties);

	/**
	 * 获取总记录数
	 * 
	 * @param entrepot
	 * @return
	 */
	public Long getCount(Dao dao);

	/**
	 * 根据仓库名称剖刀信息
	 * 
	 * @param name
	 * @return
	 */
	public Dao findByDaoName(String name);

	/**
	 * 添加或者修改剖刀信息
	 * 
	 * @param entrepot
	 */
	public void save(Dao dao);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Dao findById(Integer id);

	/**
	 * 根据id删除剖刀信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	public List<Dao> findByName(String string);
}
