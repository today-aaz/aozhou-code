package com.aozhou.code.domain.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * @Author: Aozhou
 * @Date: 2024/12/22
 */

/**
 * 权限实体类
 * <p>
 * 该类对应于数据库中的sys_permission表，存储系统中的权限信息。
 * </p>
 */
public class SysPermission implements Serializable {

    /**
     * 权限ID
     * <p>
     * 每个权限在系统中都有唯一的标识符。
     * </p>
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     * <p>
     * 例如：查看用户、编辑角色等。
     * </p>
     */
    private String permissionName;

    /**
     * 权限编码
     * <p>
     * 权限的唯一标识符，如 PERMISSION_VIEW_USER。
     * </p>
     */
    private String permissionCode;

    /**
     * 权限对应的URL
     * <p>
     * 在Web应用中，权限通常与特定的URL路径相关联。
     * </p>
     */
    private String url;

}
