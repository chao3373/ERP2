package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import com.shenke.entity.OutIn;

/**
 * 出入库Service接口
 * 
 * @author Administrator
 *
 */
public interface OutInService {
	/**
	 * 分页查询出入库信息
	 * 
	 * @param entrepot
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<OutIn> list(OutIn outIn, Integer page, Integer rows, Direction asc, String... properties);

	/**
	 * 获取总记录数
	 * 
	 * @param entrepot
	 * @return
	 */
	public Long getCount(OutIn outIn);

	/**
	 * 根据仓库名称出入库信息
	 * 
	 * @param name
	 * @return
	 */
	public OutIn findByOutInName(String name);

	/**
	 * 添加或者修改出入库信息
	 * 
	 * @param entrepot
	 */
	public void save(OutIn outIn);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public OutIn findById(Integer id);

	/**
	 * 根据id删除出入库信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);
}
