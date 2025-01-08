package com.aozhou.code.config;

import cn.dev33.satoken.stp.StpInterface;
import com.aozhou.code.common.respose.ErrorCode;
import com.aozhou.code.domain.dao.SysPermission;
import com.aozhou.code.domain.dao.SysRole;
import com.aozhou.code.exception.BusinessException;
import com.aozhou.code.service.PermissionService;
import com.aozhou.code.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: Aozhou
 * @Date: 2025/1/7
 */
@Component
@Slf4j
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 根据登录id查询权限列表
        if (loginId == null ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"查询权限列表时，loginId 参数为空");
        }
        Long userId;
        try {
            userId = Long.valueOf(loginId.toString());
        } catch (NumberFormatException e) {
            log.error("权限查询失败：loginId={} 类型错误", loginId);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数 loginId 类型错误，请提供 Long 类型的用户 ID");
        }
        List<SysPermission> permission = permissionService.getPermissionByUserId(userId);
        // 返回数据库中获取到的权限列表
        return permission.stream()
                .map(SysPermission::getPermission)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 根据登录id查询角色列表
        if (loginId == null ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"查询角色列表时，loginId 参数为空");
        }
        Long userId;
        try {
            userId = Long.valueOf(loginId.toString());
        } catch (NumberFormatException e) {
            log.error("角色查询失败：loginId={} 类型错误", loginId);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数 loginId 类型错误，请提供 Long 类型的用户 ID");
        }
        List<SysRole> role = roleService.getRoleByUserId(userId);
        // 返回数据库中获取到的权限列表
        return role.stream()
                .map(SysRole::getRoleName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
