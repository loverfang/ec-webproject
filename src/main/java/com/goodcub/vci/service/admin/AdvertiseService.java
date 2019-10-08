package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Advertise;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
public interface AdvertiseService {

    TableDataInfo queryAdvertiseVoList(Map<String,Object> params, int pageNum, int pageSize);

    Integer saveAdvertise(Advertise advertise);

    Integer updateAdvertise(Advertise advertise);

    Integer deleteAdvertise(List<Integer> idList);
}
