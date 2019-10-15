package com.goodcub.vci.service.site;

import com.goodcub.common.page.TableDataInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/15
 * @Version V1.0
 **/
public interface NewsPhotoFrontService {
    TableDataInfo queryNewsPhotoFrontList(Long nid, int pageNum, int pageSize);
}
