package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.vci.entity.Advertise;
import com.goodcub.vci.mapper.AdvertiseMapper;
import com.goodcub.vci.service.admin.AdvertiseService;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.vo.admin.AdvertiseVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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
    public TableDataInfo queryAdvertiseVoList(Map<String,Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"sindex asc, addtime desc");
        List<AdvertiseVO> advertiseVOList = advertiseMapper.queryAdvertiseVoList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<AdvertiseVO> pageInfo = new PageInfo<>(advertiseVOList, pageSize);
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setTotal(pageInfo.getTotal());

        return tableDataInfo;
    }

    @Override
    public Integer saveAdvertise(Advertise advertise) {
        return advertiseMapper.insertAdvertise(advertise);
    }

    @Override
    public Integer updateAdvertise(Advertise advertise) {
        return advertiseMapper.updateAdvertise(advertise);
    }

    @Override
    @Transactional
    public Integer deleteAdvertise(List<Integer> idList) {
        return advertiseMapper.deleteAdvertise(idList);
    }
}
