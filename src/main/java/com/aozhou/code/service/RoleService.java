package com.aozhou.code.service;

import com.aozhou.code.domain.dao.SysRole;

import java.util.List;

/**
 * @Author: Aozhou
 * @Date: 2024/12/26
 */
public interface RoleService {

    /**
     * 根据用户id获取角色列表
     * @param userId 用户id
     * @return 角色列表
     */
    List<SysRole> getRoleByUserId(Long userId);
}
