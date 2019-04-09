package com.shenke.service;

import java.util.List;
import com.shenke.entity.PlantType;

public interface PlantTypeService {
	/**
	 * 根据父节点查找所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	public List<PlantType> findByParentId(Integer parentId);

	/**
	 * 根据id查询PlantType信息
	 * 
	 * @param id
	 * @return
	 */
	public PlantType findById(Integer id);

	/**
	 * 添加或修改厂商信息
	 * 
	 * @param parentGoodsType
	 */
	public void save(PlantType parentGoodsType);

	/**
	 * 根据id删除厂商信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
