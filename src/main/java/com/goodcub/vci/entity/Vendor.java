package com.goodcub.vci.entity;

import com.goodcub.common.enums.RecomendEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Vendor
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/60:50
 * @Version 1.0
 */
@Data
public class Vendor implements Serializable {
    protected Integer pid;
    protected Integer cid;
    protected String name;
    protected String content;
    protected String coverImg;
    protected Integer viewcount;
    protected RecomendEnum ifindex;
    protected Integer sindex;
    protected String intro;
    protected String doman;
    protected String info;
    protected String introduction;
    protected String solutions;
    protected String highlights;
    protected String pubtime;
    protected String pkeywords;
    protected String ptitle;
    protected String pdescription;
}
