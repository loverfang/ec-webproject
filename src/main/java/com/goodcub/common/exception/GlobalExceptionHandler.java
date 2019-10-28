package com.goodcub.common.exception;

import com.goodcub.auth.exception.TokenException;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.exception.DownloadException;
import com.goodcub.vci.exception.UploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/16
 * @Version V1.0
 *
 * eg:
 * if(CollectionUtils.isEmpty(articleList)){
 *     throw new CmsBusinessException(CmsErrorCodeEnum.ARTICLE_NOT_EXIST);
 * }
 **/
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截未知错误异常
     *
     * @param request 请求
     * @param e       未知异常
     * @return 通用返回格式
     */
    @ExceptionHandler(Exception.class)
    public JsonResult cmsException(HttpServletRequest request, Exception e) {
        if ("org.apache.catalina.connector.ClientAbortException".equals(e.getClass().getName())) {
            logger.error("发生clientAbortException");
            return null;
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json;charset=UTF-8");

        logger.error("请求的url为{}出现系统异常,异常信息为:", request.getRequestURI(), e);
        return JsonResult.error(GlobalExceptionCodeEnum.ERROR.getCode(), GlobalExceptionCodeEnum.ERROR.getMessage());
    }

    /**
     * 拦截UploadException
     *
     * @param request 请求
     * @param e       业务异常
     * @return 通用返回格式
     */
    @ExceptionHandler(TokenException.class)
    public JsonResult tokenException(HttpServletRequest request, TokenException e) {
        logger.error("请求的url为{}出现业务异常,异常信息为:", request.getRequestURI(), e);
        return JsonResult.error(e.getExceptionEnum().getCode(),  e.getExceptionEnum().getMessage());
    }

    /**
     * 拦截UploadException
     *
     * @param request 请求
     * @param e       业务异常
     * @return 通用返回格式
     */
    @ExceptionHandler(UploadException.class)
    public JsonResult uploadException(HttpServletRequest request, UploadException e) {
        logger.error("请求的url为{}出现业务异常,异常信息为:", request.getRequestURI(), e);
        return JsonResult.error(e.getExceptionEnum().getCode(),  e.getExceptionEnum().getMessage());
    }

    /**
     * DownloadException
     *
     * @param request 请求
     * @param e       业务异常
     * @return 通用返回格式
     */
    @ExceptionHandler(DownloadException.class)
    public JsonResult downloadException(HttpServletRequest request, DownloadException e) {
        logger.error("请求的url为{}出现业务异常,异常信息为:", request.getRequestURI(), e);
        return JsonResult.error(e.getExceptionEnum().getCode(),  e.getExceptionEnum().getMessage());
    }


}
