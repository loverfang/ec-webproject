package com.goodcub.vci.entity;

import com.goodcub.common.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/16
 * @Version V1.0
 **/
@Data
public class NewsAd implements Serializable {
  private Integer id;
  private Long nid;
  private String title;
  private String coverImg;
  private String link;
  private StatusEnum state;
  private String uptime;
}
