package com.goodcub.vci.service.site.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.mapper.VendorMapper;
import com.goodcub.vci.service.site.VendorFrontService;
import com.goodcub.vci.vo.site.VendorFrontVO;
import com.goodcub.vci.vo.site.VendorListFrontVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName VendorFrontServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/1323:02
 * @Version 1.0
 */
@Service
public class VendorFrontServiceImpl implements VendorFrontService {

    @Resource
    VendorMapper vendorMapper;

    @Override
    public TableDataInfo queryVendorFrontList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"ifindex desc, sindex asc, pubtime desc");
        List<VendorListFrontVO> vendorList = vendorMapper.queryVendorFrontList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<VendorListFrontVO> pageInfo = new PageInfo<>(vendorList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(pageInfo.getTotal());
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setPageNum(pageInfo.getPageNum());
        tableDataInfo.setPageSize(pageInfo.getPageSize());
        tableDataInfo.setPages(pageInfo.getPages());

        return tableDataInfo;
    }

    @Override
    public VendorFrontVO queryVendorFrontInfo(Integer pid) {
        return vendorMapper.queryVendorFrontInfo(pid);
    }

}
