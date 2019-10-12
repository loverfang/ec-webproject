package com.goodcub.vci.entity;

import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
@Data
public class Autologin implements Serializable {
    private Integer aid;
    private String uname;
    private String sessionId;
    private String validtime;
}
