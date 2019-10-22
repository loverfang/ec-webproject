package com.goodcub.vci.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long fid;

    protected String name;
    protected String filePath;
    protected String extName;
    protected Long psize;
    protected StatusEnum status;
    protected Integer downCount;
    protected String uptime;
}
