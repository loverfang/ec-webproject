package com.goodcub.vci.vo.site;

import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/11
 * @Version V1.0
 **/
@Data
public class PartnerFrontVO {
    private String ptitle;
    private String pdescription;
    private String pkeywords;
    private Long nid;
    private String coverImg;
    private String content;
    private String author;
}
