//package com.aozhou.code.sys.shiro.realm;
//
//import com.aozhou.code.domain.dao.SysUser;
//import com.aozhou.code.exception.BusinessException;
//import com.aozhou.code.service.UserService;
//import com.aozhou.code.sys.shiro.JwtToken;
//import com.aozhou.code.utils.JWTUtils;
//import io.jsonwebtoken.Claims;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @Author: Aozhou
// * @Date: 2024/12/22
// */
//
//@Component
//@Slf4j
//public class ShiroRealm extends AuthorizingRealm {
//
//
//    @Resource
//    private JWTUtils jwtUtils;
//
//    @Resource
//    private UserService userService;
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    /**
//     * 大坑！，必须重写此方法，不然Shiro会报错
//     */
//
//    @Override
//    public boolean supports(AuthenticationToken token) {
//        return token instanceof JwtToken;
//    }
//
//
//   /* *
//     * 认证 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
//     */
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
//        String jwtToken = (String) auth.getCredentials();
//        String username = jwtUtils.getClaimsByToken(jwtToken).getSubject();
//        SysUser user = userService.getUserByName(username);
//          if (user == null) {
//            throw new BusinessException(400,"用户不存在");
//        }
//        // 验证秘钥是否有效
//        Claims claims = jwtUtils.getClaimsByToken(jwtToken);
//        if (jwtUtils.isTokenExpired(claims.getExpiration())) {
//            throw new BusinessException(400, "token过期，请重新登录");
//        }
//        return new SimpleAuthenticationInfo(user, jwtToken, getName());
//
//    }
//    /**
//     *  授权 ，只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
//    */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
// String username = token.get().toString();
//        SysUser user = userService.getUserByName(username);
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        // TODO 添加用户角色和权限
//        simpleAuthorizationInfo.addRole("1");
//        log.info("用户权限：{}", "1");
//        Set<String> permission = new HashSet<>();
//        permission.add("aa");
//        simpleAuthorizationInfo.addStringPermissions(permission);
//        log.info("用户权限：{}", "aa");
//        return simpleAuthorizationInfo;
//
//        return null;
//    }
//}
