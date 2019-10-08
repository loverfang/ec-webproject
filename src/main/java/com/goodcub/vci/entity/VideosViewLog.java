package com.goodcub.vci.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class VideosViewLog implements Serializable {
    private Integer vlid;
    private Integer vid;
    private Integer memid;
    private String title;
    private Integer usecount;
    private String vtime;
}
