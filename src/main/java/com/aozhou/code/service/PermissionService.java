package com.aozhou.code.service;

import com.aozhou.code.domain.dao.SysPermission;

import java.util.List;

/**
 * @Author: Aozhou
 * @Date: 2024/12/26
 */
public interface PermissionService {
    /**
     * 根据用户id查询权限列表
     * @param userId 用户id
     * @return 权限列表
     */
    List<SysPermission> getPermissionByUserId(Long userId);
}
