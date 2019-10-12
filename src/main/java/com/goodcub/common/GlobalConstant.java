package com.goodcub.common;

/**
 * @ClassName GlobalConstant
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/411:31
 * @Version 1.0
 */
public class GlobalConstant {
    public static String LOGINED_USER_KEY = "_logined_user_id";

    //保存cookie时的cookieName
    public final static String COOKIE_DOMAIN_NAME = "com.vcintegration";
    //加密cookie时的网站自定码
    public final static String WEB_KEY = "_vci";
    //设置cookie有效期是15天，根据需要自定义
    public final static long COOKIE_MAX_AGE = 60 * 60 * 24 * 15;

}
