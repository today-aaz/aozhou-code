package com.aozhou.code.domain.mapper;

import com.aozhou.code.domain.dao.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Aozhou
 * @Date: 2024/12/22
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select\n" +
            "    sys_roles.id,\n" +
            "    sys_roles.role_name,\n" +
            "    sys_roles.role_desc,\n" +
            "    sys_roles.created_at,\n" +
            "    sys_roles.updated_at\n" +
            "from sys_roles\n" +
            "join sys_user_roles on sys_roles.id = sys_user_roles.role_id\n" +
            "where user_id = #{userId}")
    List<SysRole> getRoleByUserId(Long userId);
}
