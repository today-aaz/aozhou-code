package com.aozhou.code.domain.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

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
@Data
@TableName("sys_permissions")
public class SysPermission implements Serializable {

    /**
     * 权限ID
     * <p>
     * 每个权限在系统中都有唯一的标识符。
     * </p>
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     * <p>
     * 例如：查看用户、编辑角色等。
     * </p>
     */
    @TableField("permission")
    private String permission;

    /**
     * 权限编码
     * <p>
     * 权限的唯一标识符，如 PERMISSION_VIEW_USER。
     * </p>
     */
    @TableField("permission_desc")
    private String permissionDesc;

    /**
     * 创建时间
     */
    @TableField("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

}
