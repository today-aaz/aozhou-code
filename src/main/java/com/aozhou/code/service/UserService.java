package com.aozhou.code.service;

import com.aozhou.code.domain.dto.RegisterDto;
import com.aozhou.code.domain.dao.SysUser;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: Aozhou
 * @Date: 2024/12/22
 */
public interface UserService {

    /**
     *  注册用户
     * @param registerDto 注册信息
     * @return
     */
    void register(RegisterDto registerDto);

    /**
     * 登录用户
     * @param username 用户名
     * @param password 密码
     * @return
     */
    Map<String, String> login(String username, String password, HttpServletResponse response);

    /**
     *  根据用户名称获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    SysUser getUserByName(String username);


}
