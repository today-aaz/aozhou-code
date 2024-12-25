package com.aozhou.code.common.respose;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 * @Author: Aozhou
 */
@Data
public class BaseResponse <T> implements Serializable {

    private static final long serialVersionUID = 1L;  // 推荐添加 serialVersionUID

    private int code;       // 响应码，通常是业务状态码
    private T data;         // 响应数据
    private String message; // 响应消息，通常是错误消息或成功消息

    // 标准的构造方法，包含 code, data 和 message
    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message != null ? message : "";
    }

    // 只包含 data，适用于没有错误消息时
    public BaseResponse(T data) {
        this(0, data, "");  // 默认 code 为 0，表示成功
    }

    // 只包含 code 和 data
    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    // 根据 ErrorCode 构造，通常用于错误响应
    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }

    // 默认构造方法
    public BaseResponse() {
        this(0, null, "");
    }
}
