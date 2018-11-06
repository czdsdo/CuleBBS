package com.example.admin.shiro;

import com.example.admin.service.AdminUserService;
import com.example.admin.service.PermissionService;
import com.example.common.entity.AdminUser;
import com.example.common.entity.Permission;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;

public class MyshiroRealm extends AuthorizingRealm {
    @Resource
    private AdminUserService adminUserService;
    @Resource
    private PermissionService permissionService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer id = (Integer) principalCollection.getPrimaryPrincipal();
        List<Permission> permissionList = permissionService.loadUserPermission(id);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        permissionList.forEach(p -> info.addStringPermission(p.getPerurl()));
        return info;
    }

    /**
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        AdminUser user = adminUserService.findByUserName(username);
        if (null == user) throw new UnknownAccountException();
        if (0 == user.getEnable()) {
            throw new LockedAccountException();
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(user.getId(), user.getPassword(), ByteSource.Util.bytes(username), getName());
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("AdminSession", user);
        session.setAttribute("AdminSessionId", user.getId());
        return authorizationInfo;

    }
}
