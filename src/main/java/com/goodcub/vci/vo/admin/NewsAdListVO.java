package com.goodcub.vci.vo.admin;

import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/16
 * @Version V1.0
 **/
@Data
public class NewsAdListVO {
   private Integer id;
   private Long nid;
   private String coverImg;
   private String title;
   private String link;
   private String state;
   private String uptime;
}
