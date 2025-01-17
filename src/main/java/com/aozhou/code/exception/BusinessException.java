package com.aozhou.code.exception;

import com.aozhou.code.common.respose.ErrorCode;

/**
 * @Author: Aozhou
 * @Date: 2024/12/25
 */
public class BusinessException extends RuntimeException{

    /**
     * 错误码
     */
    private final Integer code;


    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }

}
