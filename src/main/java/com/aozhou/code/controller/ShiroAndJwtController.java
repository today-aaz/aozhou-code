package com.aozhou.code.controller;

import com.aozhou.code.common.respose.BaseResponse;
import com.aozhou.code.common.respose.ErrorCode;
import com.aozhou.code.common.respose.ResultUtils;
import com.aozhou.code.domain.RegisterDto;
import com.aozhou.code.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: Aozhou
 * @Date: 2024/12/22
 */

@Slf4j
@RestController
@RequestMapping("/shiro")
@Api(tags = "shiro")
public class ShiroAndJwtController {


    @Resource
    private UserService  userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResponse login(@RequestParam String username,@RequestParam String password, HttpServletResponse response){
        if (username == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "用户名不能为空");
        }
        if (password == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "密码不能为空");
        }
        // 其他逻辑判断等
        Map<String, String> map = userService.login(username, password, response);
        return ResultUtils.success(map);

    }
    @ApiOperation("用户登出")
    @PostMapping("/logout")
    public BaseResponse logout(){
        // TODO 用户退出 JWt 无法直接删除 token 可使用黑名单机制等
        SecurityUtils.getSubject().logout();
        return ResultUtils.success(null);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public BaseResponse register(@RequestBody RegisterDto registerDto){
        // 用户名 密码 不能为空
        if (registerDto.getUsername() == null ) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "用户名不能为空");
        }
        if (registerDto.getPassword() == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "密码不能为空");
        }
        userService.register(registerDto);
        return ResultUtils.success(ErrorCode.SUCCESS);
    }


}
