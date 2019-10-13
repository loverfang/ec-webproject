package com.goodcub.vci.service.site;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.vo.site.VendorFrontVO;

import java.util.Map;

/**
 * @ClassName VendorFrontService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/1323:01
 * @Version 1.0
 */
public interface VendorFrontService {
    TableDataInfo queryVendorFrontList(Map<String,Object> params, int pageNum, int pageSize);
    VendorFrontVO queryVendorFrontInfo(Integer pid);
}
