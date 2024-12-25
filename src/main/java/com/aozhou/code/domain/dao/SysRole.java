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
 * 角色实体类
 * <p>
 * 该类对应于数据库中的sys_role表，用于存储系统中的角色信息。
 * </p>
 */

@Data
@TableName("sys_roles")
public class SysRole implements Serializable {

    /**
     * 角色ID
     * <p>
     * 每个角色在系统中都有唯一的标识符。
     * </p>
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     * <p>
     * 例如：管理员、用户等。
     * </p>
     */
    private String roleName;

    /**
     * 角色编码
     * <p>
     * 角色的唯一标识符，如 ROLE_ADMIN。
     * </p>
     */
    private String roleCode;


}
