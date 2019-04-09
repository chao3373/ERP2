package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import com.shenke.entity.Receipt;

/**
 * 收付款方式Service接口
 * 
 * @author Administrator
 *
 */
public interface ReceiptService {
	/**
	 * 根据dep_id查询员工
	 * 
	 * @param id
	 * @return
	 */
	public List<Receipt> findByReceiptTypeId(Integer id);

	/**
	 * 分页查询员工信息
	 * 
	 * @param Receipt
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<Receipt> list(Receipt Receipt, Integer page, Integer rows, Direction asc, String... string);

	/**
	 * 查询员工信息数量
	 * 
	 * @param goods
	 * @return
	 */
	public Long getCount(Receipt Receipt);

	/**
	 * 保存员工信息
	 * 
	 * @param Receipt
	 */
	public void save(Receipt Receipt);

	/**
	 * 根据id查询员工信息
	 * 
	 * @param id
	 * @return
	 */
	public Receipt findById(Integer id);

	/**
	 * 根据id删除员工
	 * 
	 * @param id
	 */
	public void deleteById(Integer id);
}
