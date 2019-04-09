package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.entity.Letter;
import com.shenke.entity.Log;
import com.shenke.service.LetterService;
import com.shenke.service.LogService;

/**
 * 印字设置Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/letter")
public class LetterAdminController {

	@Resource
	private LetterService letterService;

	@Resource
	private LogService logService;

	/**
	 * 分页查询印字信息
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = "印字设置")
	public Map<String, Object> list(Letter letter, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Letter> entrepotList = letterService.list(letter, page, rows, Direction.ASC, "id");
		Long total = letterService.getCount(letter);
		resultMap.put("rows", entrepotList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询印字信息"));
		return resultMap;
	}

	/**
	 * 添加或修改仓库信息
	 */
	@RequestMapping("/save")
	@RequiresPermissions("印字设置")
	public Map<String, Object> save(Letter letter) {
		if (letter.getId() != null) {
			logService.save(new Log(Log.UPDATE_ACTION, "修改印字信息" + letter));
		} else {
			logService.save(new Log(Log.ADD_ACTION, "添加印字信息" + letter));
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (letter.getId() == null) {
			if (letterService.findByLetterName(letter.getName()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "印字已经存在！");
				return resultMap;
			}
		}
		letterService.save(letter);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 删除印字信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = "印字设置")
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除印字信息" + letterService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		letterService.delete(id);
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 下拉框模糊查询
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/letterList")
	public List<Letter> comboList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		return letterService.findByName("%" + q + "%");
	}
}
