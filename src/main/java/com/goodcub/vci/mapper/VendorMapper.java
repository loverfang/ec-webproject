package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.Vendor;
import com.goodcub.vci.vo.admin.VendorInfoVO;
import com.goodcub.vci.vo.admin.VendorListVO;
import com.goodcub.vci.vo.site.VendorFrontVO;
import com.goodcub.vci.vo.site.VendorListFrontVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName VendorMapper
 * @Description Vendor名称实则为产品信息表
 * @Author Luo.z.x
 * @Date 2019/10/60:56
 * @Version 1.0
 */
public interface VendorMapper {

    List<VendorListVO> queryVendorList(Map<String,Object> params);

    Integer insertVendor(Vendor vendor);

    Integer updateVendor(Vendor vendor);

    VendorInfoVO queryVendorInfoByPid(Integer pid);

    Integer deleteVendor(@Param("idList") List<Integer> idList);

    List<VendorListFrontVO> queryVendorFrontList(Map<String,Object> params);

    VendorFrontVO queryVendorFrontInfo(Integer pid);

    Integer countTotal();
}
