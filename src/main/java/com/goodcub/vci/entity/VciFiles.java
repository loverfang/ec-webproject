package com.goodcub.vci.entity;

import com.goodcub.common.enums.StatusEnum;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/21
 * @Version V1.0
 **/
@Data
public class VciFiles implements Serializable {
    protected Long fid;
    protected String name;
    protected String filePath;
    protected Integer psize;
    protected StatusEnum status;
    protected Integer downCount;
    protected String uptime;
}
