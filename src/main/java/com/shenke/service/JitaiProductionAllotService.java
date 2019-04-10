package com.shenke.service;

import java.util.List;

import com.shenke.entity.JitaiProductionAllot;

/**
 * 机台生产分配Service
 * @author Administrator
 *
 */
public interface JitaiProductionAllotService {

	//保存机台生产分配信息
	public void save(JitaiProductionAllot jitaiProductionAllot);

	/**
	 * 获取最大的通知单号
	 * 
	 */
	public Long findMaxInfornNumber();

	/**
	 * 查询所有生产通知单
	 * 
	 * @return
	 */
	public List<JitaiProductionAllot> list();

	/**
	 * 根据条件查询生产通知单信息
	 * @param allorTime
	 * @param jitai
	 * @return
	 */
	public Object screen(String allorTime, Integer jitai);
	
}
