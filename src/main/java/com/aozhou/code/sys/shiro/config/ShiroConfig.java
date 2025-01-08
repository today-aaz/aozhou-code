//package com.aozhou.code.sys.shiro.config;
//
//
//import com.aozhou.code.sys.shiro.filter.JWTShiroFilter;
//import com.aozhou.code.sys.shiro.realm.ShiroRealm;
//import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
//import org.apache.shiro.mgt.DefaultSubjectDAO;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import javax.servlet.Filter;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///*
// * @Author: Aozhou
// * @Date: 2024/12/22
// */
//
//@Configuration
//public class ShiroConfig {
//
//    @Bean("securityManager")
//    public DefaultWebSecurityManager getManager(ShiroRealm realm) {
//        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
//
//        // 使用自己的realm
//        manager.setRealm(realm);
//
//        /* 关闭shiro自带的session，详情见文档
//         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
//         */
//        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//        manager.setSubjectDAO(subjectDAO);
//
//        return manager;
//    }
//
//    @Bean("shiroFilter")
//    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
//        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
//        // 添加自己的过滤器并且取名为jwt
//        Map<String, Filter> filterMap = new HashMap<>();
//        filterMap.put("jwt", new JWTShiroFilter());
//        factoryBean.setFilters(filterMap);
//        factoryBean.setSecurityManager(securityManager);
//        // 配置过滤器链
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        // 不触发默认的shiro验证机制
//        filterChainDefinitionMap.put("/shiro/login", "anon");
//        // 需要认证的请求
//        filterChainDefinitionMap.put("/**", "jwt");
//        // 访问 401 和 404 页面不通过我们的 Filter
//        filterChainDefinitionMap.put("/401", "anon");
//        filterChainDefinitionMap.put("/404", "anon");
//        // 设置过滤器链规则
//        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        // 禁用 HTTP Basic 认证
//        factoryBean.setLoginUrl("/login");  // 登录页面的 URL
//        // 设置未授权页面
//        factoryBean.setUnauthorizedUrl("/401");
//        return factoryBean;
//    }
//
//
//    /*
//     * 下面的代码是添加注解支持
//     */
//    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
//        // https://zhuanlan.zhihu.com/p/29161098
//        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//        return defaultAdvisorAutoProxyCreator;
//    }
//
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }
//}