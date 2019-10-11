package com.goodcub.vci.vo.site;

import lombok.Data;

/**
 * @ClassName NewsListFrontVO
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/117:30
 * @Version 1.0
 */
@Data
public class NewsListFrontVO {
    private Long nid;
    private String nlable;
    private String endate;
    private String city;
    private String province;
    private String title;
    private String pubtime;
}
