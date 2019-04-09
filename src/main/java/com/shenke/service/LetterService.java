package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import com.shenke.entity.Letter;

/**
 * 印字设置Service
 * 
 * @author Administrator
 *
 */
public interface LetterService {
	/**
	 * 分页查询印字信息
	 * 
	 * @param entrepot
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<Letter> list(Letter letter, Integer page, Integer rows, Direction asc, String... properties);

	/**
	 * 获取总记录数
	 * 
	 * @param entrepot
	 * @return
	 */
	public Long getCount(Letter letter);

	/**
	 * 根据仓库名称印字信息
	 * 
	 * @param name
	 * @return
	 */
	public Letter findByLetterName(String name);

	/**
	 * 添加或者修改印字信息
	 * 
	 * @param entrepot
	 */
	public void save(Letter letter);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Letter findById(Integer id);

	/**
	 * 根据id删除印字信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	public List<Letter> findByName(String string);
}
