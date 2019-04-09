package com.shenke.service;

import java.util.List;
import com.shenke.entity.ReceiptType;

/**
 * 收付款方式Type Service
 * 
 * @author Administrator
 *
 */
public interface ReceiptTypeService {
	/**
	 * 根据父节点查找所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	public List<ReceiptType> findByParentId(Integer parentId);

	/**
	 * 根据id查询ReceiptType信息
	 * 
	 * @param id
	 * @return
	 */
	public ReceiptType findById(Integer id);

	/**
	 * 添加或修改厂商信息
	 * 
	 * @param parentGoodsType
	 */
	public void save(ReceiptType parentGoodsType);

	/**
	 * 根据id删除厂商信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);
}
