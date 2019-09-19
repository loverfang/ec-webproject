package com.goodcub.common.exception;

/**
 * @Author Luo.z.x
 * @Description: 统一异常码接口定义
 * @Date 2019/9/16
 * @Version V1.0
 **/
public interface ExceptionEnum {
    /**
     * 获取异常编码
     *
     * @return 异常码
     */
    Integer getCode();

    /**
     * 获取异常信息
     *
     * @return 异常信息
     */
    String getMessage();
}
