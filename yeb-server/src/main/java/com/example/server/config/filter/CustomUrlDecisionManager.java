/*
package com.example.server.config.filter;

import com.example.server.controller.LoginController;
import com.example.server.pojo.Admin;
import com.example.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

*/
/**
 * 权限控制
 * 根据登录的用户  判断用户拥有哪些角色
 *//*


@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {


    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
    //Authentication authentication    获取用户信息
        for (ConfigAttribute configAttribute : configAttributes) {   // 获取可以访问当前路径的权限
            //访问当前url所需的角色
            String needRole = configAttribute.getAttribute();


            System.out.println("允许访问的角色"+needRole);
            //判断角色是否为登录即可访问的角色,ROLE_LOGIN
            if ("ROLE_LOGIN".equals(needRole)){
                //判断登录
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("CustomUrlDecisionManager : 尚未登录,请登录!");
                }else {
                    return;
                }
            }
            //判断角色是否为url所需角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {     //获取当前用户携带的角色权限
                System.out.println("数据库的authority "+authority);
                if (authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("CustomUrlDecisionManager : 权限不足!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
*/
