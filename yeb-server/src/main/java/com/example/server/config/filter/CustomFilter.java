/*
package com.example.server.config.filter;


import com.example.server.controller.LoginController;
import com.example.server.pojo.Menu;
import com.example.server.pojo.Role;
import com.example.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

*/
/**
 * 权限控制
 * 根据请求url分析出请求所需角色
 *
 * 根据角色查询菜单,请求的url与菜单角色是否匹配
 *  spring security自定义权限拦截FilterInvocationSecurityMetadataSource
 *
 * @author
 *//*

@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IMenuService menuService;



    AntPathMatcher antPathMatcher = new AntPathMatcher();  //antPathMatcher.match路径比较

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求的url
        String requestUrl = ((FilterInvocation)o).getRequestUrl();
        // System.out.println("requestUrl = " + requestUrl);
        //获取菜单
        List<Menu> menus = menuService.getMenusWithRole();
        // System.out.println("menus = " + menus);
        for (Menu menu : menus) {
            //判断请求的url与菜单角色是否匹配      antPathMatcher.match路径比较

            // System.out.println(menu.getUrl());
            // System.out.println(requestUrl);
            // System.out.println(menu.getRoles());
       */
/*     if (antPathMatcher.match(menu.getUrl(), requestUrl)&&menu.getRoles().size()>0) {

                System.out.println(menu.getUrl());
                // System.out.println(requestUrl);
                List<Role> roles = menu.getRoles();
                System.out.println(roles);
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getName();
                }
                System.out.println(SecurityConfig.createList(values));
                return SecurityConfig.createList(values);
            }
*//*

            List<Role> roles = menu.getRoles();
            // System.out.println(roles);
            int size = roles.size();
            String[] values = new String[size];
            for (int i = 0; i < size; i++) {
                values[i] = roles.get(i).getName();
            }
            // System.out.println(SecurityConfig.createList(values));
            return SecurityConfig.createList(values);

        }



        //没匹配的url默认为登录即可访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
*/
