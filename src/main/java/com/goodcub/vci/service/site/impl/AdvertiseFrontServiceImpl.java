package com.goodcub.vci.service.site.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.mapper.AdvertiseMapper;
import com.goodcub.vci.service.site.AdvertiseFrontService;
import com.goodcub.vci.vo.site.AdvertiseFrontVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdvertiseFrontServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/110:12
 * @Version 1.0
 */
@Service
public class AdvertiseFrontServiceImpl implements AdvertiseFrontService {

    @Resource
    AdvertiseMapper advertiseMapper;

    @Override
    public TableDataInfo queryAdvertiseFrontVOList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"sindex asc, addtime desc");
        List<AdvertiseFrontVO> advertiseVOList = advertiseMapper.queryAdvertiseFrontVoList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<AdvertiseFrontVO> pageInfo = new PageInfo<>(advertiseVOList, pageSize);
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setTotal(pageInfo.getTotal());

        return tableDataInfo;
    }

}
