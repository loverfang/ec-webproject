package com.goodcub.vci.service.site;

import com.goodcub.common.page.TableDataInfo;

import java.util.Map;

/**
 * @ClassName AdvertiseFrontService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/110:11
 * @Version 1.0
 */
public interface AdvertiseFrontService {
    TableDataInfo queryAdvertiseFrontVOList(Map<String,Object> params, int pageNum, int pageSize);
}
