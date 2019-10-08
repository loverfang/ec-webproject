package com.goodcub.vci.entity;

import com.goodcub.common.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Category
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/516:24
 * @Version 1.0
 */
@Data
public class Category implements Serializable {
    private Integer cid;
    private Integer pid;
    private String cname;
    private Integer sindex;
    private StatusEnum cstate;
    private String pubtime;
    private String memo;
}
