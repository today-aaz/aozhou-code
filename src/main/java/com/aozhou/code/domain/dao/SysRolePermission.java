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
 * 角色权限关联实体类
 * <p>
 * 该类对应于数据库中的sys_role_permission表，用于管理角色与权限的关系。
 * </p>
 */
@Data
@TableName("sys_role_permissions")
public class SysRolePermission implements Serializable {

    /**
     * 关联ID
     * <p>
     * 每条记录的唯一标识符。
     * </p>
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     * <p>
     * 关联到sys_role表的角色ID，表示该角色拥有哪些权限。
     * </p>
     */
    private Long roleId;

    /**
     * 权限ID
     * <p>
     * 关联到sys_permission表的权限ID，表示该权限属于哪个角色。
     * </p>
     */
    private Long permissionId;
}
