package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;

import com.shenke.entity.Brand;

/**
 * 商标Service接口
 * @author Administrator
 *
 */
public interface BrandService {
	/**
	 * 分页查询商标信息
	 * 
	 * @param entrepot
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<Brand> list(Brand brand, Integer page, Integer rows, Direction asc, String... properties);

	/**
	 * 获取总记录数
	 * 
	 * @param entrepot
	 * @return
	 */
	public Long getCount(Brand brand);

	/**
	 * 根据仓库名称商标信息
	 * 
	 * @param name
	 * @return
	 */
	public Brand findByBrandName(String name);

	/**
	 * 添加或者修改商标信息
	 * 
	 * @param entrepot
	 */
	public void save(Brand brand);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Brand findById(Integer id);

	/**
	 * 根据id删除商标信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	public List<Brand> findByName(String string);
}
