//package com.aozhou.code.sys.security.filter;
//
//import com.aozhou.code.domain.dao.SysUser;
//import com.aozhou.code.service.UserService;
//import com.aozhou.code.utils.JWTUtils;
//import io.jsonwebtoken.Claims;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.annotation.Resource;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @Author: Aozhou
// * @Date: 2024/12/27
// */
//@Component
//@Slf4j
//public class JwtSecurityFilter extends OncePerRequestFilter {
//
//
//    @Resource
//    private JWTUtils jwtUtils;
//    @Resource
//    private UserService userService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // 执行JWT验证逻辑
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String requestURI = httpRequest.getRequestURI();
//        log.info("Request URI: {}", requestURI);
//        // 判断是否是 Swagger 或者 API 文档相关的放行 URL
//        if (    requestURI.startsWith("/swagger") ||
//                requestURI.startsWith("/v2/api-docs") ||
//                requestURI.startsWith("/v3/api-docs") ||
//                requestURI.startsWith("/swagger-resources") ||
//                requestURI.startsWith("/webjars") ||
//                requestURI.startsWith("/doc.html" )||
//                requestURI.startsWith("/favicon.ico")){
//            // 执行其他过滤器
//            filterChain.doFilter(request, response);
//            return ; // Swagger等接口无需认证
//        }
//        // 判断是否是放行的登录、注册接口
//        if (requestURI.equals("/security/login") || requestURI.equals("/security/register")) {
//            // 执行其他过滤器
//            filterChain.doFilter(request, response);
//            return ;
//        }
//        String authorization = httpRequest.getHeader("Authorization");
//        // 判断 Authorization是否为空 或是否以 Bearer 开头
//        if (authorization == null || !authorization.startsWith("Bearer ")) {
//            // 抛出认证异常，触发 Shiro 的认证失败处理逻辑
//            throw new BadCredentialsException("Invalid Authorization header format, expected Bearer <token>");
//        }
//        // 去掉 Bearer 前缀
//        String token = authorization.substring(7);
//        // 打印解析令牌
//        log.info("Processing JWT Token: {}", token);
//        // 解析和验证token
//        // 验证 token 是否有效
//        if (!jwtUtils.isValidateToken(token)) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Invalid or expired JWT token");
//            return;
//        }
//        // 解析 JWT 并获取用户信息
//        Claims claims = jwtUtils.getClaimsByToken(token);
//        String username = claims.get("username", String.class);
//        SysUser sysUser = userService.getUserByName(username);
//        if (sysUser != null ) {
//            // 设置 Spring Security 上下文 以便后续过滤器可以访问用户信息
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser, token, null);
//            // 设置请求详情（IP、会话信息等）
//            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            // 将认证信息保存到 Spring Security 上下文
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//        // 执行其他过滤器
//        filterChain.doFilter(request, response);
//
//    }
//}
