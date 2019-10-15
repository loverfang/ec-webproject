package com.goodcub.vci.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.goodcub.common.enums.NewsTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName News
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/277:33
 * @Version 1.0
 */
@Data
public class News implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long nid;
    private NewsTypeEnum ntype;
    private String author;
    private String title;
    private String content;
    private Integer sindex;
    private String ifindex;
    private Integer viewcount;
    private String coverImg;
    private String authorImg;
    private String ptitle;
    private String pkeywords;
    private String pdescription;
    private String pubtime;
}
