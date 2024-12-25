package com.aozhou.code.sys.shiro.filter;

import com.aozhou.code.common.respose.BaseResponse;
import com.aozhou.code.common.respose.ErrorCode;
import com.aozhou.code.common.respose.ResultUtils;
import com.aozhou.code.sys.shiro.JwtToken;
import com.aozhou.code.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Aozhou
 * @Date: 2024/12/22
 */
@Configuration
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");
        // 判断 Authorization 是否以 Bearer 开头
        // 如果没有 Authorization 或者格式不正确
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            // 抛出认证异常，触发 Shiro 的认证失败处理逻辑
            throw new AuthenticationException("Invalid Authorization header format, expected Bearer <token>");
        }
            // 去掉 Bearer 前缀
            String token = authorization.substring(7);
            // 打印解析令牌
            log.info("Processing JWT Token: {}", token);
            // 创建自定义的 JwtToken
            JwtToken jwtToken = new JwtToken(token);
            // 提交给 realm 进行登录，如果验证失败会抛出异常
            getSubject(request, response).login(jwtToken);
        try {
            // 提交给 Shiro 的 realm 进行登录验证
            getSubject(request, response).login(jwtToken);
            // 如果没有异常，表示登录成功
            return true;
        } catch (AuthenticationException e) {
            // 捕获认证异常并处理
            log.error("Authentication failed for token: {}", token);
            // 继续抛出认证失败的异常
            throw e;
        }
    }
    /**
     * （目前只做了是正常返回的false所有资源都需要鉴权）
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article，登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        log.info("Request URI: {}", requestURI);
        // 判断是否是 Swagger 或者 API 文档相关的放行 URL
        if (    requestURI.startsWith("/swagger") ||
                requestURI.startsWith("/v2/api-docs") ||
                requestURI.startsWith("/swagger-resources") ||
                requestURI.startsWith("/webjars") ||
                requestURI.startsWith("/doc.html")){
            return true; // Swagger等接口无需认证
        }
        // 判断是否是放行的登录、注册接口
        if (requestURI.equals("/shiro/login") || requestURI.equals("/shiro/register")) {
            return true;
        }
        // 判断是否是登录尝试，如果是，则执行登录验证
        if (isLoginAttempt(request, response)) {
            try {
                // 调用Shiro的登录方法，执行认证
                executeLogin(request, response);
                // 如果认证成功，允许访问
                return true;
            } catch (Exception e) {
                // 返回 false，认证失败
                return false;
            }
        }
        // 默认为false，需要认证
        return false;
    }

    /**
     * 对跨域提供支持
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 处理跨域
        String origin = httpServletRequest.getHeader("Origin");
        if (origin != null){
            httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        }
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 访问拒绝处理
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        BaseResponse resultUtils = ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR,"请先登录");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(GsonUtil.GsonString(resultUtils));
        return false;
    }

}
