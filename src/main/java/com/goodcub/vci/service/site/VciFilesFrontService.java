package com.goodcub.vci.service.site;

import com.goodcub.vci.vo.site.VciFileFrontVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/21
 * @Version V1.0
 **/

public interface VciFilesFrontService {
    VciFileFrontVO queryVciFile(Long fid);
}
