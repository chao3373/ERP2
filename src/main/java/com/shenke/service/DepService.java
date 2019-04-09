package com.shenke.service;

import java.util.List;

import com.shenke.entity.Dep;

/**
 * 部门管理Service
 * @author Administrator
 *
 */
public interface DepService {

	/**
	 * 根据父节点查找所有子节点
	 * @param parentId
	 * @return
	 */
	public List<Dep> findByParentId(Integer parentId);

	/**
	 * 根据id查询dep信息
	 * @param id
	 * @return
	 */
	public Dep findById(Integer id);

	/**
	 * 添加或修改部门信息
	 * @param parentGoodsType
	 */
	public void save(Dep parentGoodsType);

	/**
	 * 根据id删除部门信息
	 * @param id
	 */
	public void delete(Integer id);

	
}
