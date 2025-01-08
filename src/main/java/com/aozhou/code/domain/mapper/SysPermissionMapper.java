package com.aozhou.code.domain.mapper;

import com.aozhou.code.domain.dao.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Aozhou
 * @Date: 2024/12/22
 */
@Mapper
public interface SysPermissionMapper  extends BaseMapper<SysPermission> {

    @Select("select DISTINCT sp.* " +
            "from sys_permissions sp " +
            "join sys_role_permissions srp on sp.id = srp.permission_id " +
            "join sys_user_roles sur on srp.role_id = sur.role_id where user_id = #{userId}; ")
    List<SysPermission> getPermissionByUserId(Long userId);
}
