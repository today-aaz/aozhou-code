package com.aozhou.code.controller;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.util.SaResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Aozhou
 * @Date: 2025/1/8
 */
@Api(tags = "sa-token注解鉴权demo")
@RestController
@RequestMapping("/sa-token/")
public class SaTokenAnnotationAuthController {

    // 登录校验：只有登录之后才能进入该方法
    @ApiOperation(value = "登录校验")
    @SaCheckLogin
    @GetMapping("info")
    public String info() {
        return "查询用户信息";
    }

    // 角色校验：必须具有指定角色才能进入该方法
    @ApiOperation("角色校验")
    @SaCheckRole("admin")
    @GetMapping("add-by-role")
    public String addByRole() {
        return "角色校验：用户增加";
    }

    // 权限校验：必须具有指定权限才能进入该方法
    @ApiOperation("权限校验")
    @SaCheckPermission("user:create")
    @GetMapping("add-by-permission")
    public String addByPermission() {
        return "权限校验：用户增加";
    }

    // 二级认证校验：必须二级认证之后才能进入该方法
    @ApiOperation("二级认证校验")
    @SaCheckSafe()
    @GetMapping("add-by-safe")
    public String addBySafe() {
        return "二级认证校验：用户增加";
    }

    // Http Basic 校验：只有通过 Http Basic 认证后才能进入该方法
    @ApiOperation("Http Basic 校验")
    @SaCheckHttpBasic(account = "sa:123456")
    @GetMapping("add-by-basic")
    public String addByBasic() {
        return "Http Basic 校验：用户增加";
    }

    // Http Digest 校验：只有通过 Http Digest 认证后才能进入该方法
    @ApiOperation("Http Digest 校验")
    @SaCheckHttpDigest(value = "sa:123456")
    @GetMapping("add-by-digest")
    public String addByDigest() {
        return "Http Digest 校验：用户增加";
    }

    // 校验当前账号是否被封禁 comment 服务，如果已被封禁会抛出异常，无法进入方法
    @ApiOperation("校验当前账号是否被封禁 comment 服务")
    @SaCheckDisable("comment")
    @GetMapping("send")
    public String send() {
        return "校验封禁：查询用户信息";
    }

    // 注解式鉴权：只要具有其中一个权限即可通过校验
    @ApiOperation("权限组校验：有其一即可")
    @GetMapping("atJurOr")
    @SaCheckPermission(value = {"user:create", "user-all", "user-delete"}, mode = SaMode.OR)
    public SaResult atJurOr() {
        return SaResult.data("用户信息");
    }
    // 注解式鉴权：拥有权限才可通过校验
    @ApiOperation("权限组校验：全部拥有")
    @GetMapping("atJurAnd")
    @SaCheckPermission(value = {"user:create", "user-all", "user-delete"}, mode = SaMode.AND)
    public SaResult atJurAnd() {
        return SaResult.data("用户信息");
    }


    // 角色权限双重 “or校验”：具备指定权限或者指定角色即可通过校验
    @ApiOperation("角色权限双重 “or校验”")
    @GetMapping("userAdd")
    @SaCheckPermission(value = "user.add", orRole = "admin")
    public SaResult userAdd() {
        return SaResult.data("用户信息");
    }

    // 此接口加上了 @SaIgnore 可以游客访问
    @ApiOperation("游客访问")
    @SaIgnore
    @GetMapping("getList")
    public SaResult getList() {
        // ...
        return SaResult.ok();
    }

    // 在 `@SaCheckOr` 中可以指定多个注解，只要当前会话满足其中一个注解即可通过验证，进入方法。
    @ApiOperation("`@SaCheckOr` 其中一个注解即可通过验证 ")
    @SaCheckOr(
            login = @SaCheckLogin,
            role = @SaCheckRole("admin"),
            permission = @SaCheckPermission("user.add"),
            safe = @SaCheckSafe("update-password"),
            httpBasic = @SaCheckHttpBasic(account = "sa:123456"),
            disable = @SaCheckDisable("submit-orders")
    )
    @GetMapping("test")
    public SaResult test() {
        // ...
        return SaResult.ok();
    }



}
