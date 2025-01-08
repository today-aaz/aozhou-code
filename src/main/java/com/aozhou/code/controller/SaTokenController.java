package com.aozhou.code.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.aozhou.code.common.respose.BaseResponse;
import com.aozhou.code.common.respose.ErrorCode;
import com.aozhou.code.common.respose.ResultUtils;
import com.aozhou.code.domain.dao.SysPermission;
import com.aozhou.code.domain.dao.SysRole;
import com.aozhou.code.domain.dao.SysUser;
import com.aozhou.code.domain.dto.RegisterDto;
import com.aozhou.code.domain.vo.LoginUserVo;
import com.aozhou.code.exception.BusinessException;
import com.aozhou.code.service.PermissionService;
import com.aozhou.code.service.RoleService;
import com.aozhou.code.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Aozhou
 * @Date: 2025/1/6
 */
@RestController
@RequestMapping("/saToken")
public class SaTokenController {

    /**
     * 盐值，混淆密码
     */
    public static final String SALT = "aozhou";

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @PostMapping("/doLogin")
    public BaseResponse doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        // 根据username查询用户信息
        SysUser sysUser = userService.getUserByName(username);
        if (sysUser == null){
            return ResultUtils.error("用户不存在");
        }
        // 验证密码
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        if (! sysUser.getPassword().equals(encryptPassword)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "密码错误");
        }
        // 执行登录
        StpUtil.login(sysUser.getId(), true);
        // 用户信息保存到session
        StpUtil.getSession().set("user", sysUser);
        Map<String, Object> map = new HashMap<>();
        map.put("sa-token", StpUtil.getTokenValue());
        return ResultUtils.success(map);
    }
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public BaseResponse register(@RequestBody @Valid RegisterDto registerDto){
        userService.register(registerDto);
        return ResultUtils.success(ErrorCode.SUCCESS);
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
//    @GetMapping("/isLogin")
//    public String isLogin() {
//        return "当前会话是否登录：" + StpUtil.isLogin();
//    }


    @ApiOperation("用户登出")
    @PostMapping("/logout")
    public BaseResponse logout(){
        // 当前会话 退出登录
        StpUtil.logout();
        // 当前会话删除session信息
        StpUtil.getSession().logout();
        return ResultUtils.success("退出登录成功");
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getUserInfo")
    public BaseResponse getUserInfo(){
        StpUtil.isLogin();
        Long id = StpUtil.getLoginIdAsLong();
//        SysUser user = userService.getUserByName(SecurityUtils.getUsername());
        // TODO 后续需要专门的vo类 例如longinUser
        SysUser sysUser = (SysUser)StpUtil.getSession().get("user");
        LoginUserVo loginUser = new LoginUserVo();
        loginUser.setId(id);
        loginUser.setUsername(sysUser.getUsername());
        loginUser.setPhone(sysUser.getPhone());
        loginUser.setEmail(sysUser.getEmail());
        // 获取用户权限和角色等
        List<SysRole> sysRoles = roleService.getRoleByUserId(id);
        List<SysPermission> sysPermissions = permissionService.getPermissionByUserId(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", loginUser);
        map.put("roles",sysRoles);
        map.put("permission", sysPermissions);

        return ResultUtils.success(map);
    }



}
