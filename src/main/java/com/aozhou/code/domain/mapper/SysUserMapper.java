package com.aozhou.code.domain.mapper;

import com.aozhou.code.domain.dao.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: Aozhou
 * @Date: 2024/12/22
 */

/**
 * 用户表的 Mapper 接口
 * <p>
 * 该接口继承自 MyBatis-Plus 提供的 BaseMapper，自动提供 CRUD 操作。
 * </p>
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser>{


    @Select("select * from  sys_users where username = #{username}")
    SysUser selectByName (String username);

}
