package com.goodcub.auth.exception;

import com.goodcub.common.exception.ExceptionEnum;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/16
 * @Version V1.0
 **/
public enum TokenExceptionCodeEnum  implements ExceptionEnum {

    // Token认证状态码
    TOKEN_IS_NULL(50008, "Token值为空"),
    TOKEN_NOT_lOGGED(50012, "已在其他设备上/浏览器上登录!"),
    TOKEN_NOT_EXPIRED(50014, "JWT非法或已超时,重新登录!");

    TokenExceptionCodeEnum(Integer code, String message) {
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
