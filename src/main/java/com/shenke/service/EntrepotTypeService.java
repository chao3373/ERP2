package com.shenke.service;

import java.util.List;

import com.shenke.entity.EntrepotType;

/**
 * 仓位Type Service
 * 
 * @author Administrator
 *
 */
public interface EntrepotTypeService {
	/**
	 * 根据父节点查找所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	public List<EntrepotType> findByParentId(Integer parentId);

	/**
	 * 根据id查询EntrepotType信息
	 * 
	 * @param id
	 * @return
	 */
	public EntrepotType findById(Integer id);

	/**
	 * 添加或修改厂商信息
	 * 
	 * @param parentGoodsType
	 */
	public void save(EntrepotType parentGoodsType);

	/**
	 * 根据id删除厂商信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
