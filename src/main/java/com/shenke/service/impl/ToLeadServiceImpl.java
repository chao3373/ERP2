package com.shenke.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shenke.service.ToLeadService;

/**
 * 导入销售订单实现类
 * @author Administrator
 *
 */
@Service("toLeadService")
public class ToLeadServiceImpl implements ToLeadService{

	@Override
	public Map<Integer, Map<Integer, Object>> addSaleInfo(MultipartFile file) {
		Map<Integer, Map<Integer, Object>> map = new HashMap<Integer, Map<Integer,Object>>();
		
		return null;
	}
	
}
