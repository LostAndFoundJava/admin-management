package com.oukingtim.config.myListen;

import com.oukingtim.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by chenjian on 2018/6/8.
 */
public class MySessionListener implements SessionListener {
    @Autowired
    private CacheManager cacheManager;

    private Cache<String, String> uniqueLoginUser;
    @Override
    public void onStart(Session session) {//会话创建触发 已进入shiro的过滤连就触发这个方法
        // TODO Auto-generated method stub
        System.out.println("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {//退出
        // TODO Auto-generated method stub
        System.out.println("退出会话：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {//会话过期时触发
        // TODO Auto-generated method stub
        System.out.println("会话过期：" + session.getId());
        String username = (String) session.getAttribute("username");
        uniqueLoginUser = cacheManager.getCache("uniqueLoginUser");
        uniqueLoginUser.remove(username);
    }

}