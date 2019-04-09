package com.shenke.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 导入销售订单Service
 * @author Administrator
 *
 */
public interface ToLeadService {

	/**
	 * 解析excel文件
	 * @param file
	 * @return
	 */
	Map<Integer, Map<Integer, Object>> addSaleInfo(MultipartFile file);
	
}
