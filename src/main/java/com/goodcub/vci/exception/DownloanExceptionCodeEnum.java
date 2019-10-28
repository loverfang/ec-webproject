package com.goodcub.vci.exception;

import com.goodcub.common.exception.ExceptionEnum;

/**
 * @Author Luo.z.x
 * @Description: 上传文件异常枚举值
 * @Date 2019/9/29
 * @Version V1.0
 **/
public enum DownloanExceptionCodeEnum implements ExceptionEnum {

    // Multi上传状态码
    DOWNLOAN_FILE_NO_EXISTS(70003, "下载的文件不存在!");

    DownloanExceptionCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
