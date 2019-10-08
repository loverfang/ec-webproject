package com.goodcub.vci.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Video
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/717:10
 * @Version 1.0
 */
@Data
public class Videos implements Serializable {
    protected Integer vid;
    protected String title;
    protected String vurl;
    protected String coverImg;
    protected Integer needcount;
    protected String memo;
    protected Integer sindex;
    protected Integer viewcount;
    protected String addtime;
}
