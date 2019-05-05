package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import com.shenke.entity.Clerk;

/**
 * 员工Service接口
 * @author Administrator
 *
 */
public interface ClerkService {

	/**
	 * 根据dep_id查询员工
	 * @param id
	 * @return
	 */
	public  List<Clerk> findByDepId(Integer id);

	/**
	 * 分页查询员工信息
	 * @param clerk
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<Clerk> list(Clerk clerk, Integer page, Integer rows, Direction asc, String... string);

	/**
	 * 查询员工信息数量
	 * @param goods
	 * @return
	 */
	public Long getCount(Clerk clerk);

	/**
	 * 保存员工信息
	 * @param clerk
	 */
	public void save(Clerk clerk);

	/**
	 * 根据id查询员工信息
	 * @param id
	 * @return
	 */
	public Clerk findById(Integer id);

	/**
	 * 根据id删除员工
	 * @param id
	 */
	public void deleteById(Integer id);

	/**
	 * 下拉框模糊查询
	 * @param string
	 * @return
	 */
	public List<Clerk> findByName(String string);

	/**
	 * 模糊查询所有生产员工
	* @Description:
	* @Param:
	* @return:
	* @Author: Andy
	* @Date:
	*/
	public List<Clerk> findByProName(String name);
	
}
