package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import com.shenke.entity.Product;
import com.shenke.entity.Sell;

/**
 * 产品及原料Service
 * 
 * @author Administrator
 *
 */
public interface ProductService {
	/**
	 * 根据dep_id查询员工
	 * 
	 * @param id
	 * @return
	 */
	public List<Product> findByProductTypeId(Integer id);

	/**
	 * 分页查询员工信息
	 * 
	 * @param Plant
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<Product> list(Product Plant, Integer page, Integer rows, Direction asc, String... string);

	/**
	 * 查询员工信息数量
	 * 
	 * @param goods
	 * @return
	 */
	public Long getCount(Product plant);

	/**
	 * 保存员工信息
	 * 
	 * @param Plant
	 */
	public void save(Product plant);

	/**
	 * 根据id查询员工信息
	 * 
	 * @param id
	 * @return
	 */
	public Product findById(Integer id);

	/**
	 * 根据id删除员工
	 * 
	 * @param id
	 */
	public void deleteById(Integer id);

	/**
	 * 下拉框模糊查询
	 * 
	 * @param string
	 * @return
	 */
	public List<Product> findByName(String string);
}
