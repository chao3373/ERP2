package com.shenke.service;

import java.util.List;
import com.shenke.entity.ClientType;

/**
 * 客户关系Service
 * 
 * @author Administrator
 *
 */
public interface ClientTypeService {

	/**
	 * 根据父节点查找所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	public List<ClientType> findByParentId(Integer parentId);

	/**
	 * 根据id查询ClientType信息
	 * 
	 * @param id
	 * @return
	 */
	public ClientType findById(Integer id);

	/**
	 * 添加或修改厂商信息
	 * 
	 * @param parentGoodsType
	 */
	public void save(ClientType parentGoodsType);

	/**
	 * 根据id删除厂商信息
	 * 
	 * @param id
	 */
	public void delete(Integer id);

}
