package com.shenke.util;

import com.shenke.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 字符串工具类
 * 
 * @author Administrator
 *
 */
public class StringUtil {

	@Resource
	private StorageService storageService;

	/**
	 * 判断是否为空
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		if (string == null || "".equals(string.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否不是空
	 * 
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		if ((string != null) && !"".equals(string.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成四位编号
	 * 
	 * @param code
	 * @return
	 */
	public static String formatCode(String code) {
		int length = code.length();
		Integer num = Integer.parseInt(code.substring(length - 4, length)) + 1;
		String codeNum = num.toString();
		int codeLength = codeNum.length();
		for (int i = 4; i > codeLength; i--) {
			codeNum = "0" + codeNum;
		}
		return codeNum;
	}
}
