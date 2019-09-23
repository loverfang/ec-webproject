package com.goodcub.vci.entity;

import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
@Data
public class Advertise {
    protected Integer adid;
    protected String adtitle;
    protected String adurl;
    protected String status;
    protected String Integer;
    protected String coverPath;
    protected Date addtime;
}
