package com.goodcub.vci.entity;

import com.goodcub.common.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName NewsPdf
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/38:37
 * @Version 1.0
 */
@Data
public class NewsPdf implements Serializable {
    private Integer pid;
    private Long nid;
    private String name;
    private String pdfPath;
    private String coverImg;
    private String isdisplay;
    private String isupfile;
    private StatusEnum state;
    private String pdfname;
    private String intor;
    private String psize;
    private Integer sindex;
    private Integer downcount;
    private String addtime;
    private String source;
}
