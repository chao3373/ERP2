package com.shenke.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 打印设置Service
 * 
 * @author Administrator
 *
 */
public interface PrintSetService {

	/**
	 * 打印设置
	 * 
	 * @param fields
	 */
	public void printSet(HttpServletRequest request, String fields);

	/**
	 * 获取设置的打印列
	 */
	public String[] getSelectRows(HttpServletRequest request);

}
