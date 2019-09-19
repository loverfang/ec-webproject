package com.goodcub.common.exception;

/**
 * @Author Luo.z.x
 * @Description: 异常父类
 * @Date 2019/9/16
 * @Version V1.0
 **/
public class BaseException extends RuntimeException {
    private Integer code;
    private String message;

    public BaseException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}