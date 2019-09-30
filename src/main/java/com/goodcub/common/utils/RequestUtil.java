package com.goodcub.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ClassName RequestUtil
 * @Description 获取Request对象相关信息的工具类
 * @Author Luo.z.x
 * @Date 2019/9/300:17
 * @Version 1.0
 */
public class RequestUtil {

    private final static String ServerPort = "80";

    /**
     * 获取request对象
     *
     * @return request
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 项目的真实路径
     *
     * @return String
     */
    public static String getPjoPath() {
        try {
            return // 项目的真实路径
                    StringUtils.replace(getRequest().getServletContext().getContextPath(), "/", "\\");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取客户端请求的路径名，如：/object/delObject
     *
     * @return String
     */
    public static String getServletPath() {
        try {
            return // 项目的真实路径
                    getRequest().getServletPath();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取服务器地址，如：localhost
     *
     * @return String
     */
    public static String getServerName() {
        try {
            return // 项目的真实路径
                    getRequest().getServerName();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取服务器端口，如8080
     *
     * @return String
     */
    public static String getServerPort() {
        try {
            return // 项目的真实路径
                    getRequest().getServerPort() + "";
        } catch (Exception e) {
            return ServerPort;
        }
    }

    /**
     * 获用户地址，如：127.0.0.1
     *
     * @return String
     */
    public static String getRemoteAddr() {
        try {
            String remoteAddr = getRequest().getHeader("X-Real-IP");
            if (StringUtils.isNotBlank(remoteAddr)) {
                remoteAddr = getRequest().getHeader("X-Forwarded-For");
            } else if (StringUtils.isNotBlank(remoteAddr)) {
                remoteAddr = getRequest().getHeader("Proxy-Client-IP");
            } else if (StringUtils.isNotBlank(remoteAddr)) {
                remoteAddr = getRequest().getHeader("WL-Proxy-Client-IP");
            }
            return remoteAddr != null ? remoteAddr : getRequest().getRemoteAddr();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取项目的访问路径，如： localhost:8080/xx
     *
     * @return String
     */
    public static String getObjUrl() {
        return getServerName() + ":" + getServerPort() + getServletPath();
    }
}