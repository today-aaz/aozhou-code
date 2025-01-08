package com.aozhou.code.config.web;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Aozhou
 * @Date: 2024/12/28
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册 Sa-Token 拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，定义详细认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 指定一条 match 规则
            SaRouter
                    .match("/**")
                    .notMatch("/saToken/doLogin",
                            "/saToken/register",
                            "/swagger**/**",
                            "/v2/api-docs",
                            "/swagger-resources/**",
                            "/webjars/**",
                            "/doc.html")
                    .check(r -> StpUtil.checkLogin());
        })).addPathPatterns("/**");

    }
}
