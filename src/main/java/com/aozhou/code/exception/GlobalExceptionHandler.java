package com.aozhou.code.exception;

import cn.dev33.satoken.exception.*;
import com.aozhou.code.common.respose.BaseResponse;
import com.aozhou.code.common.respose.ErrorCode;
import com.aozhou.code.common.respose.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: Aozhou
 * @Date: 2024/12/25
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 通用异常处理
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse<?> genericExceptionHandler(Exception e) {
        log.error("Exception", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "未知错误：" + e.getMessage());
    }

    /**
     * 业务异常处理
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    /**
     * 系统运行时异常处理
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }

//
//        /**
//         * 处理 Spring Security 认证异常
//         * @param e
//         * @return 返回 401 Unauthorized
//         */
//        @ExceptionHandler(AuthenticationException.class)
//        public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
//            // 处理 Spring Security 认证异常，返回 401 Unauthorized
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + e.getMessage());
//        }

    /**
     * Sa-Token 未登录异常处理
     */
    @ExceptionHandler(NotLoginException.class)
    public BaseResponse<?> handleNotLoginException(NotLoginException e) {
        String message;
        switch (e.getType()) {
            case NotLoginException.NOT_TOKEN:
                message = "Token 未提供";
                break;
            case NotLoginException.INVALID_TOKEN:
                message = "无效的 Token";
                break;
            case NotLoginException.TOKEN_TIMEOUT:
                message = "Token 已过期";
                break;
            case NotLoginException.BE_REPLACED:
                message = "Token 已被顶下线";
                break;
            case NotLoginException.KICK_OUT:
                message = "Token 已被踢下线";
                break;
            default:
                message = "当前会话未登录";
        }
        log.warn("NotLoginException: {}", message);
        return ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR, message);
    }

    /**
     * Sa-Token 无权限异常处理
     */
    @ExceptionHandler(NotPermissionException.class)
    public BaseResponse<?> handleNotPermissionException(NotPermissionException e) {
        String message = "无权限访问：" + e.getCode();
        log.warn("NotPermissionException: {}", message);
        return ResultUtils.error(ErrorCode.NO_AUTH_ERROR, message);
    }

    /**
     * Sa-Token 无角色异常处理
     */
    @ExceptionHandler(NotRoleException.class)
    public BaseResponse<?> handleNotRoleException(NotRoleException e) {
        String message = "无角色访问权限：" + e.getRole();
        log.warn("NotRoleException: {}", message);
        return ResultUtils.error(ErrorCode.NO_AUTH_ERROR, message);
    }

    /**
     * Sa-Token 服务封禁异常处理
     */
    @ExceptionHandler(DisableServiceException.class)
    public BaseResponse<?> handleDisableServiceException(DisableServiceException e) {
        String message = "服务被封禁：" + e.getService() + "，解封时间：" + e.getDisableTime();
        log.warn("DisableServiceException: {}", message);
        return ResultUtils.error(ErrorCode.FORBIDDEN_ERROR, message);
    }

    /**
     * Sa-Token 安全认证失败异常处理
     */
    @ExceptionHandler(NotSafeException.class)
    public BaseResponse<?> handleSafeException(NotSafeException e) {
        String message = "二级认证失败：" + e.getMessage();
        log.warn("SafeException: {}", message);
        return ResultUtils.error(ErrorCode.NO_AUTH_ERROR, message);
    }

    /**
     * 通用 Sa-Token 异常处理
     */
    @ExceptionHandler(SaTokenException.class)
    public BaseResponse<?> handleSaTokenException(SaTokenException e) {
        log.warn("SaTokenException: {}", e.getMessage());
        return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR, "鉴权失败：" + e.getMessage());
    }

}
