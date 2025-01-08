//package com.aozhou.code.controller;
//
//import com.aozhou.code.common.respose.BaseResponse;
//import com.aozhou.code.common.respose.ErrorCode;
//import com.aozhou.code.common.respose.ResultUtils;
//import com.aozhou.code.domain.dao.SysPermission;
//import com.aozhou.code.domain.dao.SysRole;
//import com.aozhou.code.domain.dao.SysUser;
//import com.aozhou.code.domain.dto.RegisterDto;
//import com.aozhou.code.domain.mapper.SysUserMapper;
//import com.aozhou.code.service.PermissionService;
//import com.aozhou.code.service.RoleService;
//import com.aozhou.code.service.UserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Author: Aozhou
// * @Date: 2024/12/22
// */
//
//@Slf4j
//@RestController
//@RequestMapping("/shiro")
//@Api(tags = "shiro")
//public class ShiroAndJwtController {
//
//
//    @Resource
//    private UserService  userService;
//    @Resource
//    private SysUserMapper userMapper;
//    @Resource
//    private RoleService roleService;
//    @Resource
//    private PermissionService permissionService;
//
//    @ApiOperation("用户登录")
//    @PostMapping("/login")
//    public BaseResponse login(@RequestParam @Valid String username, @RequestParam @Valid String password, HttpServletResponse response){
//        // 其他逻辑判断等
//        Map<String, String> map = userService.login(username, password, response);
//        return ResultUtils.success(map);
//
//    }
//    @ApiOperation("用户登出")
//    @PostMapping("/logout")
//    public BaseResponse logout(){
//        // TODO 用户退出 JWt 无法直接删除 token 可使用黑名单机制等
////        SecurityUtils.getSubject().logout();
//        return ResultUtils.success(null);
//    }
//
//    @ApiOperation("用户注册")
//    @PostMapping("/register")
//    public BaseResponse register(@RequestBody @Valid RegisterDto registerDto){
//        userService.register(registerDto);
//        return ResultUtils.success(ErrorCode.SUCCESS);
//    }
//
//    @ApiOperation("获取用户信息")
//    @GetMapping("/getUserInfo")
//    public BaseResponse getUserInfo(){
//        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
//        Long userId = user.getId();
//        SysUser sysUser = new SysUser();
//        sysUser.setId(userId);
//        sysUser.setUsername(user.getUsername());
//        sysUser.setPhone(user.getPhone());
//        sysUser.setEmail(user.getEmail());
//        // 获取用户权限和角色等
//        List<SysRole> sysRoles = roleService.getRoleByUserId(userId);
//        List<SysPermission> sysPermissions = permissionService.getPermissionByUserId(userId);
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("user", sysUser);
//        map.put("roles",sysRoles);
//        map.put("permission", sysPermissions);
//
//        return ResultUtils.success(map);
//    }
//
//}
