package com.shenke.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import com.shenke.service.PrintSetService;

/**
 * 打印设置Service实现类
 * 
 * @author Administrator
 *
 */
@Service("printSetService")
public class PrintSetServiceImpl implements PrintSetService {

	@Override
	public void printSet(HttpServletRequest request, String fields) {
		String[] fieldList = fields.split(",");
		System.out.println(fieldList.length);
		request.getSession().getServletContext().setAttribute("fieldList", fieldList);
	}

	@Override
	public String[] getSelectRows(HttpServletRequest request) {
		if (request.getAttribute("fieldList") != null) {
			return (String[]) request.getAttribute("fieldList");
		} else {
			return null;
		}
	}

}
