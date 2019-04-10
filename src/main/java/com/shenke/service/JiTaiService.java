package com.shenke.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort.Direction;

import com.shenke.entity.JiTai;

/**
 * 机台Service接口
 * 
 * @author Administrator
 *
 */
public interface JiTaiService {
	/**
	 * 分页查询机台信息
	 * 
	 * @param entrepot
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<JiTai> list(JiTai jitai, Integer page, Integer rows, Direction asc, String... properties);

	/**
	 * 获取总记录数
	 * 
	 * @param entrepot
	 * @return
	 */
	public Long getCount(JiTai jitai);

	/**
	 * 根据仓库名称机台信息
	 * 
	 * @param name
	 * @return
	 */
	public JiTai findByJiTaiName(String name);

	/**
	 * 添加或者修改机台信息
	 * 
	 * @param entrepot
	 */
	public void save(JiTai jitai);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public JiTai findById(Integer id);

	/**
	 * 根据id删除机台信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 下拉框模糊查询机台信息
	 * @param string
	 * @return
	 */
	public List<JiTai> findByName(String string);

	/**
	 * 查询所有机台信息
	 * @return
	 */
	public List<JiTai> findAll();

}
