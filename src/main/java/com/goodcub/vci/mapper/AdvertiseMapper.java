package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.Advertise;
import com.goodcub.vci.vo.admin.AdvertiseVO;
import com.goodcub.vci.vo.site.AdvertiseFrontVO;
import org.apache.ibatis.annotations.Param;

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
     * 后台--查询广告列表
     * @param params
     * @return
     */
    List<AdvertiseVO> queryAdvertiseVoList(Map<String,Object> params);

    /**
     * 新增广告信息
     * @param advertise
     * @return
     */
    Integer insertAdvertise(Advertise advertise);

    /**
     * 更新广告信息
     * @param advertise
     * @return
     */
    Integer updateAdvertise(Advertise advertise);

    /**
     * 删除广告信息
     * @param idList
     * @return
     */
    Integer deleteAdvertise(@Param("idList") List<Integer> idList);

    /**
     * 前台--查询广告列表
     * @param params
     * @return
     */
    List<AdvertiseFrontVO> queryAdvertiseFrontVoList(Map<String,Object> params);

}
