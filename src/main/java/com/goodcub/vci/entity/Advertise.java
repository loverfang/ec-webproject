package com.goodcub.vci.entity;

import com.goodcub.common.enums.StatusEnum;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
@Data
public class Advertise implements Serializable {
    protected Integer adid;
    protected String adtitle;
    protected String adurl;
    protected String coverImg;
    protected StatusEnum status;
    protected Integer sindex;
    protected String addtime;
}
