package com.goodcub.vci.entity;

import com.goodcub.common.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName TfcType
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2020/2/298:01
 * @Version 1.0
 */
@Data
public class TfcType  implements Serializable {
    protected Integer typeId;
    protected String typeName;
    protected Integer sindex;
    protected StatusEnum tstate;
    protected String memo;
    protected String addTime;
}
