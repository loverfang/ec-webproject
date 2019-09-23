package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.Advertise;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
public interface AdvertiseMapper {

    /**
     * 查询广告列表
     * @param params
     * @return
     */
    List<Advertise> queryAdvertiseList(Map<String,Object> params);

}