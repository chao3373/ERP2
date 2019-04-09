package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.shenke.entity.Log;
import com.shenke.entity.Role;
import com.shenke.entity.User;
import com.shenke.entity.UserRole;
import com.shenke.service.LogService;
import com.shenke.service.RoleService;
import com.shenke.service.UserRoleService;
import com.shenke.service.UserService;
import com.shenke.util.StringUtil;

/**
 * 后台管理用户Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserAdminController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private LogService logService;
	
	
	/**
	 * 分页查询用户信息
	 * @param user
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	@RequiresPermissions(value="用户管理")
	public Map<String, Object> list(User user, @RequestParam(value="page",required=false)Integer page, @RequestParam(value="rows",required=false)Integer rows) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<User> userList = userService.list(user, page, rows, Direction.ASC, "id");
		for (User u : userList) {
			List<Role> roleList = roleService.findByUserId(u.getId());
			StringBuffer sb = new StringBuffer();
			for (Role r : roleList) {
				sb.append("," + r.getName());
			}
			u.setRoles(sb.toString().replaceFirst(",", ""));
		}
		Long total = userService.getCount(user);
		resultMap.put("rows", userList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询用户信息"));
		System.out.println(resultMap);
		return resultMap;
	}
	
	/**
	 * 保存用户角色设置
	 * @param roleIds
	 * @param userId
	 * @return
	 */
	@RequestMapping("/saveRoleSet")
	@ResponseBody
	@RequiresPermissions(value="用户管理")
	public Map<String, Object> saveRoleSet(String roleIds, Integer userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		userRoleService.deleteByUserId(userId);
		if (StringUtil.isNotEmpty(roleIds)) {
			String roleIdStr[] = roleIds.split(",");
			for (int i = 0; i < roleIdStr.length; i++) {
				UserRole userRole = new UserRole();
				userRole.setUser(userService.findById(userId));
				userRole.setRole(roleService.findById(Integer.parseInt(roleIdStr[i])));
				userRoleService.save(userRole);
			}
		}
		resultMap.put("success", true);
		logService.save(new Log(Log.UPDATE_ACTION, "保存用户角色设置"));
		return resultMap;
	}
	
	/**
	 * 添加或者修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	@RequiresPermissions(value="用户管理")
	public Map<String, Object> save(User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(user.getId()==null) {
			if(userService.findByUserName(user.getUserName())!=null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "用户名已经存在！");
				return resultMap;
			}
		}
		if(user.getId()!=null) {
			logService.save(new Log(Log.UPDATE_ACTION, "更新用户信息"+user));
		}else {
			logService.save(new Log(Log.ADD_ACTION, "添加用户信息"+user));
		}
		userService.save(user);
		resultMap.put("success", true);
		return resultMap;
	}
	
	/**
	 * 删除用户信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@RequiresPermissions(value=("用户管理"))
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除用户信息"+userService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		userRoleService.deleteByUserId(id);
		userService.delete(id);
		resultMap.put("success", true);
		return resultMap;
	}
	
	/**
	 * 修改密码
	 * @param newPassword
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword")
	@ResponseBody
	@RequiresPermissions(value="修改密码")
	public Map<String,Object> modifyPassword(String newPassword,HttpSession session)throws Exception{
		Map<String,Object> resultMap=new HashMap<>();
		User currentUser=(User) session.getAttribute("currentUser");
		User user=userService.findById(currentUser.getId());
		user.setPassword(newPassword);
		userService.save(user);
		resultMap.put("success", true);
		logService.save(new Log(Log.UPDATE_ACTION,"修改密码"));
		return resultMap;
	}
	
	/**
	 * 安全退出
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/logout")
	@RequiresPermissions(value="安全退出")
	public String logout(HttpSession session)throws Exception{
		logService.save(new Log(Log.LOGOUT_ACTION,"用户注销"));
		SecurityUtils.getSubject().logout();
		return "redirect:/login.html";
	}
	
}
