package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import com.shenke.entity.Client;

/**
 * 客户Service
 * 
 * @author Administrator
 *
 */
public interface ClientService {

	/**
	 * 根据dep_id查询员工
	 * 
	 * @param id
	 * @return
	 */
	public List<Client> findByClientTypeId(Integer id);

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
	public List<Client> list(Client Plant, Integer page, Integer rows, Direction asc, String... string);

	/**
	 * 查询员工信息数量
	 * 
	 * @param goods
	 * @return
	 */
	public Long getCount(Client plant);

	/**
	 * 保存员工信息
	 * 
	 * @param Plant
	 */
	public void save(Client plant);

	/**
	 * 根据id查询员工信息
	 * 
	 * @param id
	 * @return
	 */
	public Client findById(Integer id);

	/**
	 * 根据id删除员工
	 * 
	 * @param id
	 */
	public void deleteById(Integer id);

	/**
	 * 根据名称模糊查询
	 * @param string
	 * @return
	 */
	public List<Client> findByName(String string);

    public Client findName(String clname);
}
