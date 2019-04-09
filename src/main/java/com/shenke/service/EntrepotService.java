package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import com.shenke.entity.Entrepot;

/**
 * 仓库设置Service接口
 * 
 * @author Administrator
 *
 */
public interface EntrepotService {

	/**
	 * 根据dep_id查询员工
	 * 
	 * @param id
	 * @return
	 */
	public List<Entrepot> findByEntrepotTypeId(Integer id);

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
	public List<Entrepot> list(Entrepot Plant, Integer page, Integer rows, Direction asc, String... string);

	/**
	 * 查询员工信息数量
	 * 
	 * @param goods
	 * @return
	 */
	public Long getCount(Entrepot plant);

	/**
	 * 保存员工信息
	 * 
	 * @param Plant
	 */
	public void save(Entrepot plant);

	/**
	 * 根据id查询员工信息
	 * 
	 * @param id
	 * @return
	 */
	public Entrepot findById(Integer id);

	/**
	 * 根据id删除员工
	 * 
	 * @param id
	 */
	public void deleteById(Integer id);
}
