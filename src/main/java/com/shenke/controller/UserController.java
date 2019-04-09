package com.shenke.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.entity.Log;
import com.shenke.entity.Menu;
import com.shenke.entity.Role;
import com.shenke.entity.User;
import com.shenke.service.LogService;
import com.shenke.service.MenuService;
import com.shenke.service.RoleService;
import com.shenke.service.UserService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 用户Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	private RoleService roleService;

	@Resource
	private LogService logService;
	
	@Resource
	private MenuService menuService;

	@RequestMapping("/login")
	public Map<String, Object> login(@Valid User user, BindingResult bindingResult, HttpSession session) {
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取subject对象
		Subject subject = SecurityUtils.getSubject();
		// 认证用户名密码
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try {
			
			subject.login(token);
			
			// 查询当前登录的 用户
			String userName = (String) SecurityUtils.getSubject().getPrincipal();
			System.out.println(userName);
			User currentUser = userService.findByUserName(userName);
			System.out.println(currentUser.getId());
			session.setAttribute("currentUser", currentUser);
			List<Role> roles = roleService.findByUserId(currentUser.getId());
			map.put("roles", roles);
			map.put("rolesSize", roles.size());
			map.put("success", true);
			logService.save(new Log(Log.LOGIN_ACTION, "用户登录"));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("errorInfo", "用户名或密码错误！");
			return map;
		}

	}
	
	/**
	 * 保存角色信息
	 * @param roleId
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveRole")
	public Map<String, Object> saveRole(Integer roleId, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Role currentRole = roleService.findById(roleId);
		session.setAttribute("currentRole", currentRole);
		map.put("success", true);
		return map;
	}
	
	/**
	 * 加载当前用户信息
	 * @param session
	 * @return
	 */
	@GetMapping("/loadUserInfo")
	public String loadUserInfo(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		Role currentRole = (Role) session.getAttribute("currentRole");
		return "欢迎您："+currentUser.getTrueName()+"&nbsp;[&nbsp;"+currentRole.getName()+"&nbsp;]";
	}
	
	
	/**
	 * 加载权限菜单
	 * @param session
	 * @param parentId
	 * @return
	 */
	@PostMapping("/loadMenuInfo")
	public String loadMenuInfo(HttpSession session, Integer parentId) {
		Role currentRole = (Role) session.getAttribute("currentRole");
		return getAllMenuByParentId(parentId, currentRole.getId()).toString();
	}

	/**
	 * 获取所有菜单信息
	 * @param parentId
	 * @param roleId
	 * @return
	 */
	private JsonArray getAllMenuByParentId(Integer parentId, Integer roleId) {
		JsonArray jsonArray = this.getMenuByParentId(parentId, roleId);
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObject = (JsonObject) jsonArray.get(i);
			if("open".equals(jsonObject.get("state").getAsString())) {
				continue;
			} else {
				jsonObject.add("children", getAllMenuByParentId(jsonObject.get("id").getAsInt(), roleId));
			}
		}
		return jsonArray;
	}

	/**
	 * 根据父节点和用户角色Id查询菜单
	 * @param parentId
	 * @param roleId
	 * @return
	 */
	private JsonArray getMenuByParentId(Integer parentId, Integer roleId) {
		List<Menu> menulList = menuService.findByParentIdAndRoleId(parentId, roleId);
		JsonArray jsonArray = new JsonArray();
		for (Menu menu : menulList) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id", menu.getId());//节点id
			jsonObject.addProperty("text", menu.getName());//节点名称
			if(menu.getState()==1) {
				jsonObject.addProperty("state", "closed");//根节点
			} else {
				jsonObject.addProperty("state", "open");//子节点
			}
			jsonObject.addProperty("iconCls", menu.getIcon());//节点图片
			JsonObject attributeObject = new JsonObject();//扩展属性
			attributeObject.addProperty("url", menu.getUrl());//菜单请求地址
			jsonObject.add("attributes", attributeObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
}
