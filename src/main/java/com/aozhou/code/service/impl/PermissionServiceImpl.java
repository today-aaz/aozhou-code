package com.aozhou.code.service.impl;

import com.aozhou.code.domain.dao.SysPermission;
import com.aozhou.code.domain.mapper.SysPermissionMapper;
import com.aozhou.code.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Aozhou
 * @Date: 2024/12/26
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private SysPermissionMapper permissionMapper;
    @Override
    public List<SysPermission> getPermissionByUserId(Long userId) {
       return permissionMapper.getPermissionByUserId(userId);
    }
}
