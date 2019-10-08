package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Vendor;
import com.goodcub.vci.vo.admin.VendorInfoVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName VendorService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/61:05
 * @Version 1.0
 */
public interface VendorService {

    /**
     * 查询新闻列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryVendorList(Map<String,Object> params, int pageNum, int pageSize);

    Integer insertVendor(Vendor vendor);

    Integer updateVendor(Vendor vendor);

    VendorInfoVO queryVendorInfoByPid(Integer pid);

    Integer deleteVendor(Map<String,Object> param);
}


