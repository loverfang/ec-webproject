package com.goodcub.vci.exception;

import com.goodcub.common.exception.BusinessException;
import com.goodcub.common.exception.ExceptionEnum;

/**
 * @ClassName DownloadException
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/2823:10
 * @Version 1.0
 */
public class DownloadException  extends BusinessException {

    public DownloadException(DownloanExceptionCodeEnum downloanExceptionCodeEnum) {
        super(downloanExceptionCodeEnum);
    }
}
