package com.goodcub.vci.vo.site;

import com.goodcub.common.enums.RecomendEnum;
import lombok.Data;

/**
 * @ClassName NewsInfoFrontVO
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/117:29
 * @Version 1.0
 */
@Data
public class NewsFrontVO {
    private Long nid;
    private String ntype;
    private String title;
    private RecomendEnum ifindex;
    private Integer asviewcount;
    private String content;
    private String author;
    private String ptitle;
    private String pkeywords;
    private String pdescription;

    private String nlable;
    private String endate;
    private String province;
    private String city;
    private String ndigest;
    private String video;
    private String txt1;
    private String txt2;
    private String txt3;
    private String txt4;
    private String txt5;
    private String txt6;

    private String coverImg;
    private String authorImg;

}
