package com.shenke.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shenke.entity.Test;

@RestController
@RequestMapping("/import")
public class ImportController {

	@Autowired
	private ImportService importService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String uploadExcel(HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		InputStream inputStream = null;
		List<List<Object>> list = null;
		MultipartFile file = multipartRequest.getFile("filename");
		if (file.isEmpty()) {
			return "文件不能为空";
		}
		inputStream = file.getInputStream();
		list = importService.getBankListByExcel(inputStream, file.getOriginalFilename());
		inputStream.close();
		System.out.println(list);
		System.out.println("====================================");
		System.out.println(list.get(0));
		List<Test> testlList = new ArrayList<Test>();
		for (List<Object> list2 : list) {
			Test test = new Test();
			test.setName(list2.get(0).toString());
			test.setWidth(Double.parseDouble(list2.get(1).toString()));
			test.setHigh(Double.parseDouble(list2.get(2).toString()));
			test.setLength(Double.parseDouble(list2.get(3).toString()));
			test.setColor(list2.get(4).toString());
			test.setNum((int)Double.parseDouble(list2.get(5).toString()));
			
			testlList.add(test);
		}
		System.out.println(testlList);
		return "上传成功";
	}

}
