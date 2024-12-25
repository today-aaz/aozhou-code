package com.aozhou.code.domain.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Aozhou
 * @Date: 2024/12/22
 */

/**
 * 用户实体类
 * <p>
 * 该类对应于数据库中的sys_user表，存储用户基本信息，如用户名、密码、邮箱等。
 * </p>
 */
@Data
@TableName(value ="sys_users")
public class SysUser implements Serializable {

    /**
     * 用户ID
     * <p>
     * 唯一标识每个用户，在数据库中作为主键。
     * </p>
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     * <p>
     * 用户登录使用的唯一标识符，用于验证用户身份。
     * </p>
     */
    private String username;

    /**
     * 加密后的密码
     * <p>
     * 存储用户密码的哈希值，防止明文密码泄露。
     * </p>
     */
    private String password;

    /**
     * 盐值
     * <p>
     * 用于加密密码的随机值，确保每次密码加密结果不同，提高安全性。
     * </p>
     */
    private String salt;

    /**
     * 用户邮箱
     * <p>
     * 用户的电子邮件地址，可能用于找回密码或作为通知渠道。
     * </p>
     */
    private String email;

    /**
     * 用户手机号
     * <p>
     * 用户的手机号码，用于身份验证或系统通知。
     * </p>
     */
    private String phoneNumber;

}
