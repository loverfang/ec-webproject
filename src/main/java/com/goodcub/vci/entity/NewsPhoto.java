package com.goodcub.vci.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName NewsPhoto
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/38:31
 * @Version 1.0
 */
@Data
public class NewsPhoto implements Serializable {
    private Long pid; // 图片ID
    private Long nid; // 新闻ID
    private String ptitle; //图片名称
    private String imgPath;
    private String psize;
    private String sindex;
    private String downcount;
    private String uptime;
}
