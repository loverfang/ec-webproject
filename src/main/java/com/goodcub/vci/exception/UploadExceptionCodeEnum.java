package com.goodcub.vci.exception;

import com.goodcub.common.exception.ExceptionEnum;

/**
 * @Author Luo.z.x
 * @Description: 上传文件异常枚举值
 * @Date 2019/9/29
 * @Version V1.0
 **/
public enum UploadExceptionCodeEnum  implements ExceptionEnum {

    // Multi上传状态码
    MULTI_IS_NULL(60001, "上传文件为空!"),
    MULTI_TYPE_ERROR(60002, "不允许上传的文件类型!"),
    MULTI_SIZE_ERROR(60003, "上传文件大小超限!");

    UploadExceptionCodeEnum(Integer code, String message) {
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
