package com.oukingtim.config;

import com.oukingtim.domain.SysUser;
import com.oukingtim.service.SysMenuService;
import com.oukingtim.service.SysUserService;
import com.oukingtim.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by oukingtim
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> passwordRetryCache;

    private Cache<String, String> uniqueLoginUser;

    /**
     * @Author : oukingtim
     * @Description : 授权(验证权限时调用)
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser)principalCollection.getPrimaryPrincipal();
        String userId = user.getId();
        //用户权限列表
        Set<String> permsSet = sysMenuService.getPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * @Author : oukingtim
     * @Description : 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

//        uniqueLoginUser = cacheManager.getCache("uniqueLoginUser");
//        if(uniqueLoginUser.get(username) != null)
//            throw new UnknownAccountException("此账号已经登陆！！请勿重复登陆");

        passwordRetryCache = cacheManager.getCache("passwordRetryCache");

        AtomicInteger retryCount = passwordRetryCache.get(username);

        if (retryCount != null && retryCount.intValue() >= 5)
            throw new ExcessiveAttemptsException("输入错误超过五次，账号锁定5分钟,忘记密码请联系管理员");

        //查询用户信息
        SysUser user = sysUserService.findByUserName(username);

        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("用户名不正确");
        }

        //密码错误
        if(!password.equals(user.getPassword())) {
            if (retryCount == null) {
                retryCount = new AtomicInteger(0);
                passwordRetryCache.put(username, retryCount);
            }
            if (retryCount.incrementAndGet() >= 5) {
                throw new ExcessiveAttemptsException("输入错误超过五次，账号锁定5分钟,忘记密码请联系管理员");
            }else {
                int i = 5 - passwordRetryCache.get(username).intValue();
                throw new IncorrectCredentialsException("用户名或密码不正确，还有"+ i + "机会");
            }
        }

        //账号禁用
        if("0".equals(user.getStatus())){
            throw new LockedAccountException("用户已被禁用,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        passwordRetryCache.remove(username);
//        uniqueLoginUser.put(username,"");
        return info;
    }
}
