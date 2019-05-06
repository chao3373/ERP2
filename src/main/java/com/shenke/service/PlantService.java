package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import com.shenke.entity.Plant;

/**
 * 厂商设置Service
 * 
 * @author Administrator
 *
 */
public interface PlantService {

	/**
	 * 根据dep_id查询员工
	 * 
	 * @param id
	 * @return
	 */
	public List<Plant> findByPlantTypeId(Integer id);

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
	public List<Plant> list(Plant Plant, Integer page, Integer rows, Direction asc, String... string);

	/**
	 * 查询员工信息数量
	 * 
	 * @param goods
	 * @return
	 */
	public Long getCount(Plant plant);

	/**
	 * 保存员工信息
	 * 
	 * @param Plant
	 */
	public void save(Plant plant);

	/**
	 * 根据id查询员工信息
	 * 
	 * @param id
	 * @return
	 */
	public Plant findById(Integer id);

	/**
	 * 根据id删除员工
	 * 
	 * @param id
	 */
	public void deleteById(Integer id);

	/** 
	* @Description: 模糊查询所有厂商 
	* @Param:
	* @return:  
	* @Author: Andy
	* @Date:  
	*/
    public List<Plant> findByName(String s);
}
