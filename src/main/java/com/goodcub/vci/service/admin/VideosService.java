package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Advertise;
import com.goodcub.vci.entity.Videos;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
public interface VideosService {

    TableDataInfo queryVideosList(Map<String, Object> params, int pageNum, int pageSize);

    Integer saveVideos(Videos videos);

    Integer updateVideos(Videos videos);

    Integer deleteVideos(List<Integer> idList);

}
