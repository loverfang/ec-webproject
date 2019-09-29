package com.goodcub.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.UUID;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/29
 * @Version V1.0
 **/
public class IdUtil {

    /**
     * 获得一个去掉‘-’的UUID
     *
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }

}
