package com.goodcub.auth.exception;

import com.goodcub.common.exception.BusinessException;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/16
 * @Version V1.0
 **/
public class TokenException extends BusinessException {
    public TokenException(TokenExceptionCodeEnum tokenExceptionCodeEnum) {
        super(tokenExceptionCodeEnum);
    }
}