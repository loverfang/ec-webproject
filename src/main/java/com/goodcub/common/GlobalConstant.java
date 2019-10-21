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
    public static final String COOKIE_DOMAIN_NAME = "com.vcintegration";
    //加密cookie时的网站自定码
    public static final String WEB_KEY = "_vci";
    //设置cookie有效期是15天，根据需要自定义
    public static final long COOKIE_MAX_AGE = 60 * 60 * 24 * 15;


    //注册相关的Email发送相关的信息
    public static final String MAILSERVERCES = "registration@vcintegration.com";
    public static final String SERVICESMAILPASSWORD = "vci2ME*";
    public static final String SERVICESMAILADDRESS = "registration@vcintegration.com";
    public static final String MAILTITLE = "VCIntegration Co.,Ltd--Regist Email!";

    public static final String CHANGE_MAILTITLE = "VCIntegration Co.,Ltd--Change Password Email!";
}
