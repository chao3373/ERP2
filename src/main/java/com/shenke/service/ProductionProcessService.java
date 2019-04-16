package com.shenke.service;

import java.util.List;

import com.shenke.entity.ProductionProcess;

/**
 * 生产加工单Service
 * @author Administrator
 *
 */
public interface ProductionProcessService {

	/**
	 * 添加生产加工单
	 * @param processpProcess
	 */
	public void save(ProductionProcess processpProcess);

	/**
	 * 根据机台id查询生产加工单
	 * @param jitaiId
	 */
	public List<ProductionProcess> selectByJitaiId(Integer jitaiId);

	/**
	 * 根据机台id和通知单号查询生产加工单
	 * @param jitai
	 * @param infor
	 * @return
	 */
	public List<ProductionProcess> selectAllByInformAndJitaiId(Integer jitai, Long infor);

	/**
	 * 根据下发状态查询生产通知单
	 * @param issueState
	 */
	public List<ProductionProcess> selectByIssueState(String issueState);

	/**
	 * 根据条件查询生产加工单
	 * @param allorTime
	 * @param jitai
	 * @return
	 */
	public List<ProductionProcess> screen(String allorTime, Integer jitai);

	/**
	 * 根据机台id、下发状态和通知单号查询生产加工单
	 * @param jitaiId
	 * @param issueState
	 * @return
	 */
	public List<ProductionProcess> selectByJitaiIdAndIssueStateAndInformNumber(Integer jitaiId, String issueState, Long infLong);

	/**
	 * 查询所有未完成的生产加工单
	 * @return
	 */
	public List<ProductionProcess> selectNoAccomplish(Integer jitaiId);

	/**
	 * 修改生产加工单状态
	 * @param string
	 * @param id
	 */
	public void updateState(String string, Integer id);

	/**
	 * 根据SaleListProductId修改生产加工单状态
	 * @param string
	 * @param id
	 */
	public void updateStateBySaleListProductId(String string, Integer id);
	
}
