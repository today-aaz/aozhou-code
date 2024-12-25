package com.aozhou.code.domain.vo;

import lombok.Data;

/**
 * @Author: Aozhou
 * @Date: 2024/12/23
 */
@Data
public class LoginVO {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;
    /**
     * 手机号
     */
    private String phoneNumber;

}
