package com.goodcub.common.jwt;

/**
 * @Author Luo.z.x
 * @Description: JWT使用常量值
 * @Date 2019/9/7
 * @Version V1.0
 **/
public class SecretConstant {

    // 签名秘钥
    public static final String BASE64SECRET = "ZW]4l5JH[m6Lm)LaQEjpb!4E0lRaG(";

    // 超时毫秒数（默认15分钟）
    public static final int EXPIRESSECOND = 15*60*1000;

    // 用于JWT加密的密匙
    public static final String DATAKEY = "u^3y6SPER41jm*fn";

}