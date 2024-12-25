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
 * 用户角色关联实体类
 * <p>
 * 该类对应于数据库中的sys_user_role表，用于管理用户与角色的关系。
 * </p>
 */
@Data
@TableName("sys_user_roles")
public class SysUserRole implements Serializable {

    /**
     * 关联ID
     * <p>
     * 每条记录的唯一标识符。
     * </p>
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     * <p>
     * 关联到sys_user表的用户ID，表示该用户所属的角色。
     * </p>
     */
    private Long userId;

    /**
     * 角色ID
     * <p>
     * 关联到sys_role表的角色ID，表示该角色分配给哪些用户。
     * </p>
     */
    private Long roleId;
}
