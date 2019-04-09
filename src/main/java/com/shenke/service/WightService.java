package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;

import com.shenke.entity.Wight;


/**
 * 重量设置Service
 * @author Administrator
 *
 */
public interface WightService {
	
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
	public List<Wight> list(Wight wight, Integer page, Integer rows, Direction asc, String... properties);

	/**
	 * 获取总记录数
	 * 
	 * @param entrepot
	 * @return
	 */
	public Long getCount(Wight wight);

	/**
	 * 根据仓库名称剖刀信息
	 * 
	 * @param name
	 * @return
	 */
	public Wight findByWightName(String name);

	/**
	 * 添加或者修改剖刀信息
	 * 
	 * @param entrepot
	 */
	public void save(Wight wight);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Wight findById(Integer id);

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
	public List<Wight> findByName(String string);
}
