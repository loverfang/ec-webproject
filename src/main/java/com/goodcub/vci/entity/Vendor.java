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
    private Integer pid;
    private Integer cid;
    private String name;
    private String content;
    private String coverImg;
    private Integer viewcount;
    private RecomendEnum ifindex;
    private Integer sindex;
    private String intro;
    private String doman;
    private String info;
    private String introduction;
    private String solutions;
    private String highlights;
    private String pubtime;
    private String pkeywords;
    private String ptitle;
    private String pdescription;
}
