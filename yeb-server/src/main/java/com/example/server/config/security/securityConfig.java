package com.example.server.config.security;

/*import com.example.server.config.filter.CustomFilter;
import com.example.server.config.filter.CustomUrlDecisionManager;*/
import com.example.server.config.jwt.JwtAuthenticationTokenFilter;
import com.example.server.config.jwt.RestAuthorizationEntryPoint;
import com.example.server.config.jwt.RestfulAccessDeniedHandler;

import com.example.server.pojo.Admin;
import com.example.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * security配置类
 */

@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private IAdminService adminService;

    //当未登录或者token失效
    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;

    //当访问接口没有权限时
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

/*
    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;
    @Autowired
    private CustomFilter customFilter;*/

    /**
     * 放行
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/login",
                "/logout",
                "/ws/**",
                "/css/**",
                "/js/**",
                "/index.html",
                "favicon.ico",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/captcha"
        );
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                // 基于token不与要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                //动态权限配置
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {

                        // o.setSecurityMetadataSource(customFilter);  //根据请求url分析出请求所需角色
                        // o.setAccessDecisionManager(customUrlDecisionManager);  //判断用户有哪些角色
                        return o;
                    }
                })
                .and()
                //禁用缓存
                .headers()
                .cacheControl();

        //添加jwt登录授权过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)  //当访问接口没有权限时     accessDeniedHandler访问拒绝处理程序
                .authenticationEntryPoint(restAuthorizationEntryPoint);  //当未登录或者token失效   authenticationEntryPoint 认证入口点

    }

    /**
     * 设置执行自定义认证登录
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    /**
     * 重写 UserDetailsService
     *
     * 登录使用    可设置用户权限
     * @return
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return username -> {
            Admin admin = adminService.getAdminByUserName(username);
            if (null != admin){
                admin.setRoles(adminService.getRolesByAdminId(admin.getId()));
                return admin;
            }
            return null;
        };
    }

    /**
     * 密码 加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

}
