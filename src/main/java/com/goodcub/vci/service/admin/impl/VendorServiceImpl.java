package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Vendor;
import com.goodcub.vci.mapper.NewsPdfMapper;
import com.goodcub.vci.mapper.VendorMapper;
import com.goodcub.vci.service.admin.VendorService;
import com.goodcub.vci.vo.admin.VendorInfoVO;
import com.goodcub.vci.vo.admin.VendorListVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName VendorServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/61:06
 * @Version 1.0
 */
@Service
public class VendorServiceImpl implements VendorService {

    @Resource
    VendorMapper vendorMapper;
    @Resource
    NewsPdfMapper newsPdfMapper;

    @Override
    public TableDataInfo queryVendorList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,"sindex asc, pubtime desc");
        List<VendorListVO> vendorList = vendorMapper.queryVendorList(params);

        TableDataInfo TableDataInfo = new TableDataInfo();
        TableDataInfo.setTotal(((List) vendorList).size());
        TableDataInfo.setItems(vendorList);
        return TableDataInfo;
    }

    @Override
    public Integer insertVendor(Vendor vendor) {
        return vendorMapper.insertVendor(vendor);
    }

    @Override
    public Integer updateVendor(Vendor vendor) {
        return vendorMapper.updateVendor(vendor);
    }

    @Override
    public VendorInfoVO queryVendorInfoByPid(Integer pid) {
        return vendorMapper.queryVendorInfoByPid(pid);
    }

    @Override
    @Transactional
    public Integer deleteVendor(Map<String,Object> param) {
        Integer result = 0;
        // 删除vendor记录
        result = vendorMapper.deleteVendor((List<Integer>)param.get("idList"));

        // 删除vendor对应的pdf文件记录
        newsPdfMapper.deleteNewsPdf(param);

        // 删除对应的文件信息,或者统一交由一个定时器来处理,文件夹下不用的图片信息
        return result;
    }

}
