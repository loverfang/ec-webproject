package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.vci.entity.Advertise;
import com.goodcub.vci.mapper.AdvertiseMapper;
import com.goodcub.vci.service.admin.AdvertiseService;
import com.goodcub.common.page.TableDataInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
@Service
public class AdvertiseServiceImpl implements AdvertiseService {

    @Resource
    AdvertiseMapper advertiseMapper;

    @Override
    public TableDataInfo allAdvertiseList(Map<String,Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Advertise> persons = advertiseMapper.queryAdvertiseList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Advertise> pageInfo = new PageInfo<>(persons, pageSize);
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setTotal(pageInfo.getTotal());

        return tableDataInfo;
    }
}
