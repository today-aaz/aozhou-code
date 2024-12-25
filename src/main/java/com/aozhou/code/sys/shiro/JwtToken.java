package com.aozhou.code.sys.shiro;

import com.aozhou.code.utils.JWTUtils;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: Aozhou
 * @Date: 2024/12/22
 */
public class JwtToken implements AuthenticationToken {

    private String username;
    private String token;

    public JwtToken(String token){
        this.token = token;
        JWTUtils jwtUtil = new JWTUtils();
        this.username = jwtUtil.getClaimFiled(token, "username");
    }

    /**
     * 类似用户名
     * @return
     */
    @Override
    public Object getPrincipal() {
        return username;
    }

    /**
     * 类似密码
     * @return
     */
    @Override
    public Object getCredentials() {
        return token;
    }
}
