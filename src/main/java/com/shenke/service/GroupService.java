package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import com.shenke.entity.Group;

public interface GroupService {

	/**
	 * 分页查询班组信息
	 * 
	 * @param entrepot
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<Group> list(Group group, Integer page, Integer rows, Direction asc, String... properties);

	/**
	 * 获取总记录数
	 * 
	 * @param entrepot
	 * @return
	 */
	public Long getCount(Group group);

	/**
	 * 根据仓库名称班组信息
	 * 
	 * @param name
	 * @return
	 */
	public Group findByGroupName(String name);

	/**
	 * 添加或者修改班组信息
	 * 
	 * @param entrepot
	 */
	public void save(Group entrepot);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Group findById(Integer id);

	/**
	 * 根据id删除班组信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	public List<Group> findByName(String string);

}
