package com.goodcub.vci.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName NewsExt
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/28:49
 * @Version 1.0
 */
@Data
public class NewsExt implements Serializable {
    private Long nid;
    private String nlable;//新闻类别或新闻标签',
    private String ndigest;//新闻摘要',
    private String endate;//英文格式的日期',
    private String province;//
    private String city;//
    private String video;//
    private String txt1;
    private String txt2;
    private String txt3;
    private String txt4;
    private String txt5;
    private String txt6;
}
