package com.goodcub.common.exception;

/**
 * @Author Luo.z.x
 * @Description: 统一的异常错误码
 * @Date 2019/9/16
 * @Version V1.0
 **/
public enum GlobalExceptionCodeEnum implements ExceptionEnum {

    // 请求成功
    SUCCESS(200,"成功"),

    // 服务器内部错误
    ERROR(500,"失败");

    private int code;
    private String message;

    GlobalExceptionCodeEnum(int value, String text) {
        this.code = value;
        this.message = text;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
