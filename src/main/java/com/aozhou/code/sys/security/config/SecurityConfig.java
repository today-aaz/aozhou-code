//package com.aozhou.code.sys.security.config;
//
//import com.aozhou.code.sys.security.filter.JwtSecurityFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
///**
// * @Author: Aozhou
// * @Date: 2024/12/27
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//
//    private final JwtSecurityFilter jwtSecurityFilter;
//
//    public SecurityConfig(JwtSecurityFilter jwtSecurityFilter) {
//        this.jwtSecurityFilter = jwtSecurityFilter;
//    }
//    public static final String[] SWAGGER_WHITELIST = {
//            "/v2/api-docs/**",
//            "/swagger-resources/configuration/ui",
//            "/swagger-resources",
//            "/swagger-resources/configuration/security",
//            "/swagger-ui.html",
//            "/css/**",
//            "/js/**",
//            "/images/**",
//            "/webjars/**",
//            "/import/test",
//            "/favicon.ico",
//            "/index",
//            "/doc.html"
//
//    };
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        return http.csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        // 放行 Swagger 相关路径
//                        .antMatchers(SWAGGER_WHITELIST).permitAll()
//                        // 放行其他需要方向的 API 路径
//                        .antMatchers("/security/login", "/security/register").permitAll()
//                        .anyRequest().authenticated())
//                .addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
//
//    /**
//     * 定义一个密码加密器，用于加密和验证用户密码
//     * @return BCryptPasswordEncoder
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * Spring Security 用于处理身份验证的核心组件
//     * @param config Authentication 对象
//     * @return 用户身份是否合法
//     * @throws Exception
//     */
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}
