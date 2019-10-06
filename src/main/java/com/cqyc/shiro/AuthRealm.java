package com.cqyc.shiro;

import com.cqyc.domain.test.Permission;
import com.cqyc.domain.test.Role;
import com.cqyc.domain.test.User;
import com.cqyc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: cqyc
 * Description:
 * Created by cqyc on 19-10-5
 */
@Slf4j
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("shiroReam授权过程中的getName---->{}",getName());
        log.info("shiroReam授权过程中的this.getClass().getName()-->{}",this.getClass().getName());
        User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();

        Set<Role> roles = user.getRoles();

        if(CollectionUtils.isNotEmpty(roles)){
            for (Role role:roles){
                roleNameList.add(role.getRname());
                Set<Permission> permissionSet = role.getPermissions();
                if(CollectionUtils.isNotEmpty(permissionSet)){
                    for (Permission permission : permissionSet) {
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;
    }

    /**
     * 认证登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        //Principal ：认证的实体信息，可以是username，也可以是数据表对应的用户的实体类类型
        User user = userService.findByUsername(username);
        log.info("shiroReam认证过程中的getName---->{}",getName());
        log.info("shiroReam认证过程中的this.getClass().getName()-->{}",this.getClass().getName());
        if(!StringUtils.equals(user.getUsername(),username)){
            throw new RuntimeException("账户信息填写错误");
        }
//        Object principal = username;
        //Credential:密码
        Object Credentials = user.getPassword();
        String realmName = this.getClass().getName();
        ByteSource bytes = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, Credentials, bytes, realmName);
        return info;
    }
}
