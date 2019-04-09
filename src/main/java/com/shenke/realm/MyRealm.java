package com.shenke.realm;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import java.util.Set;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.shenke.entity.Menu;
import com.shenke.entity.Role;
import com.shenke.entity.User;
import com.shenke.repository.MenuRepository;
import com.shenke.repository.RoleRepository;
import com.shenke.repository.UserRepository;

/**
 * 自定义Reaml
 * 
 * @author Administrator
 *
 */
public class MyRealm extends AuthorizingRealm {

	@Resource
	private UserRepository userRepository;

	@Resource
	private RoleRepository roleRepository;

	@Resource
	private MenuRepository menuRepository;

	/*
	 * 授权
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.
	 * shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("开始授权");
		// 获取当前登录的用户名
		String userName = (String) SecurityUtils.getSubject().getPrincipal();
		// 根据用户名查询用户
		User user = userRepository.findByUserName(userName);
		SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
		// 根据用户id查询拥有的角色
		System.out.println(user.getId());
		List<Role> roleliList = roleRepository.findByUserId(user.getId());
		// 创建set集合将用户所属的角色名存放到set集合中
		Set<String> roles = new HashSet<String>();
		for (Role role : roleliList) {
			roles.add(role.getName());
			// 根据角色的id查询所能使用的菜单
			List<Menu> menuList = menuRepository.findByRoleId(role.getId());
			for (Menu menu : menuList) {
				simpleAuthenticationInfo.addStringPermission(menu.getName());
			}
		}
		simpleAuthenticationInfo.setRoles(roles);
		return simpleAuthenticationInfo;
	}

	/*
	 * 身份权限认证
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache
	 * .shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		System.out.println("开始认证");
		String userName = (String) token.getPrincipal();
		User user = userRepository.findByUserName(userName);
		if (user != null) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), "xxx");
			return authcInfo;
		} else {
			return null;
		}
	}
}
