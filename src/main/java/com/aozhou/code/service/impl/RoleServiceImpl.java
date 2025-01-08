package com.aozhou.code.service.impl;

import com.aozhou.code.domain.dao.SysRole;
import com.aozhou.code.domain.mapper.SysRoleMapper;
import com.aozhou.code.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Aozhou
 * @Date: 2024/12/26
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        return sysRoleMapper.getRoleByUserId(userId);
    }
}
