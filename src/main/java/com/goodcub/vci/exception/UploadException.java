package com.goodcub.vci.exception;

import com.goodcub.auth.exception.TokenExceptionCodeEnum;
import com.goodcub.common.exception.BusinessException;
import com.goodcub.common.exception.ExceptionEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/29
 * @Version V1.0
 **/
public class UploadException extends BusinessException {
    public UploadException(UploadExceptionCodeEnum uploadExceptionCodeEnum) {
        super(uploadExceptionCodeEnum);
    }
}
