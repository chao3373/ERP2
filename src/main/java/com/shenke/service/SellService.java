package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;

import com.shenke.entity.Client;
import com.shenke.entity.Sell;

/**
 * 销售方式Service
 * @author Administrator
 *
 */
public interface SellService {
	/**
	 * 分页查询销售方式
	 * @param entrepot
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<Sell> list(Sell Sell, Integer page, Integer rows, Direction asc, String... properties);

	/**
	 * 获取总记录数
	 * @param entrepot
	 * @return
	 */
	public Long getCount(Sell entrepot);

	/**
	 * 根据销售方式查询记录
	 * @param name
	 * @return
	 */
	public Sell findBySellMethod(String method);

	/**
	 * 添加或者修改销售方式
	 * @param entrepot
	 */
	public void save(Sell entrepot);

	/**
	 * 根据id查询销售方式
	 * @param id
	 * @return
	 */
	public Sell findById(Integer id);

	/**
	 * 根据id删除销售方式
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	public List<Sell> findByName(String string);

}
