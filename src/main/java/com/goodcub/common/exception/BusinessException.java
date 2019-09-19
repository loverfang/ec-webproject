package com.goodcub.common.exception;

/**
 * @Author Luo.z.x
 * @Description: 业务自定义异常类
 * @Date 2019/9/16
 * @Version V1.0
 **/
public class BusinessException extends BaseException {

    private ExceptionEnum exceptionEnum;

    public BusinessException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
        this.exceptionEnum = exceptionEnum;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
