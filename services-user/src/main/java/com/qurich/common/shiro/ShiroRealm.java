package com.qurich.common.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;




/**
 * 自定义shiro权限认证授权
 * Principals(身份)
 * Credentials(凭证)
 * Authorization（授权）
 * Authentication（认证/鉴权）
 *
 */
public class ShiroRealm extends AuthorizingRealm {
	private Logger log=Logger.getLogger(ShiroRealm.class);
	/*@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private MenuMapper menuMapper;*/
	

	/**
     * 授权
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		/*User user = (User) SecurityUtils.getSubject().getPrincipal();
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		if(user.getId()==1) {
			Set<String> roleSet = new HashSet<>();
			roleSet.add("管理员");
			simpleAuthorizationInfo.setRoles(roleSet);
			List<Menu> permissionList = menuMapper.getMenuAll(-1);
			Set<String> permissionSet = new HashSet<>();
			for (Menu m : permissionList) {
				permissionSet.add(m.getPerms());
			}
			simpleAuthorizationInfo.setStringPermissions(permissionSet);
		}else {
			List<Role> roleList = roleMapper.getRolesByUid(user.getId());
			Set<String> roleSet = new HashSet<>();
			Set<Integer> roleIdSet = new HashSet<>();
			for (Role r : roleList) {
				roleSet.add(r.getRole_name());
				roleIdSet.add(r.getId());
			}
			simpleAuthorizationInfo.setRoles(roleSet);
			if(roleIdSet.isEmpty()) {
				roleIdSet.add(0);
			}
			List<Menu> permissionList = menuMapper.getMenuByRoles(roleIdSet,-1);
			Set<String> permissionSet = new HashSet<>();
			for (Menu m : permissionList) {
				permissionSet.add(m.getPerms());
			}
			simpleAuthorizationInfo.setStringPermissions(permissionSet);
		}
		
		return simpleAuthorizationInfo;*/
		return new SimpleAuthorizationInfo();
	}
	/**
     * 登录认证
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		/*String userName = token.getPrincipal().toString();
		String password = new String((char[]) token.getCredentials());

		User user = userMapper.getUserByUsername(userName);
		if (user == null) {
			throw new UnknownAccountException("用户名或密码错误！");
		}
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("用户名或密码错误！");
		}
		if ("1".equals(user.getStatus())) {
			throw new LockedAccountException("账号已被锁定,请联系管理员！");
		}
		return new SimpleAuthenticationInfo(user, password, getName());*/
		return null;
	}

}
