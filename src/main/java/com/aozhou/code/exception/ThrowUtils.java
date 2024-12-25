package com.aozhou.code.exception;

import com.aozhou.code.common.respose.ErrorCode;

/**
 * @Author: Aozhou
 * @Date: 2024/12/25
 */
public class ThrowUtils {

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param errorCode
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param errorCode
     * @param message
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}
