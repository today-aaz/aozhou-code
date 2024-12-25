package com.aozhou.code.service.impl;

import com.aozhou.code.common.respose.ErrorCode;
import com.aozhou.code.domain.RegisterDto;
import com.aozhou.code.domain.dao.SysUser;
import com.aozhou.code.domain.mapper.SysUserMapper;
import com.aozhou.code.exception.BusinessException;
import com.aozhou.code.service.UserService;
import com.aozhou.code.utils.JWTUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Aozhou
 * @Date: 2024/12/22
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 盐值，混淆密码
     */
    public static final String SALT = "aozhou";

    @Resource
    private SysUserMapper userMapper;

    @Resource
    private JWTUtils jwtUtils;

    @Override
    public void register(RegisterDto registerDto) {
            // 获取随机盐值
//            String salt = RandomUtil.randomString(8);
            // 1. 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + registerDto.getPassword()).getBytes());
            registerDto.setSalt(SALT);
            registerDto.setPassword(encryptPassword);
            int insert = userMapper.insert(registerDto);
            if (insert != 1){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
        }

    }

    @Override
    public Map<String, String> login(String username, String password, HttpServletResponse response) {
        SysUser sysUser = this.getUserByName(username);
        //
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        if (! sysUser.getPassword().equals(encryptPassword)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "密码错误");
        }
        String token = jwtUtils.generateToken(username);
        response.setHeader(JWTUtils.HEADER, token);
        response.setHeader("Access-control-Expost-Headers", JWTUtils.HEADER);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;

    }

    @Override
    public SysUser getUserByName(String username) {
       return userMapper.selectByName(username);

    }
}
