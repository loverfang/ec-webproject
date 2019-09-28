package com.goodcub.vci.entity;

import com.goodcub.common.enums.NewsTypeEnum;
import lombok.Data;

/**
 * @ClassName News
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/277:33
 * @Version 1.0
 */
@Data
public class News {
    private Integer nid;
    private NewsTypeEnum ntype;
    private String ifindex;
    private String title;
    private Integer sindex;
    private Integer viewcount;
    private String pubtime;
    private String content;
    private String author;
    private String ptitle;
    private String pkeywords;
    private String pdescription;

    private String coverImg;
    private String authorImg;
}
