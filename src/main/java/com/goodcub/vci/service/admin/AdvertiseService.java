package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
public interface AdvertiseService {
    TableDataInfo allAdvertiseList(Map<String,Object> params, int pageNum, int pageSize);
}
