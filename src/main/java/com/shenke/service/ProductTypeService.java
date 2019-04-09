package com.shenke.service;

import java.util.List;
import com.shenke.entity.ProductType;

/**
 * 产品及原料Type Service
 * 
 * @author Administrator
 *
 */
public interface ProductTypeService {
	/**
	 * 根据父节点查找所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	public List<ProductType> findByParentId(Integer parentId);

	/**
	 * 根据id查询ProductType信息
	 * 
	 * @param id
	 * @return
	 */
	public ProductType findById(Integer id);

	/**
	 * 添加或修改厂商信息
	 * 
	 * @param parentGoodsType
	 */
	public void save(ProductType parentGoodsType);

	/**
	 * 根据id删除厂商信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
