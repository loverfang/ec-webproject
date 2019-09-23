package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.goodcub.common.page.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Advertise;
import com.goodcub.vci.mapper.AdvertiseMapper;
import com.goodcub.vci.service.admin.AdvertiseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
public class AdvertiseServiceImpl implements AdvertiseService {

    @Resource
    AdvertiseMapper advertiseMapper;

    @Override
    public TableDataInfo allAdvertiseList(Map<String,Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Advertise> persons = advertiseMapper.queryAdvertiseList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Advertise> pageInfo = new PageInfo<>(persons);



        return null;
    }
}
