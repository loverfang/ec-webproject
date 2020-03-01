package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.TfcVideos;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
public interface TfcVideosService {

    TableDataInfo queryTfcVideosList(Map<String, Object> params, int pageNum, int pageSize);

    Integer saveTfcVideos(TfcVideos tfcVideos);

    Integer updateTfcVideos(TfcVideos tfcVideos);

    Integer deleteTfcVideos(List<Integer> idList);

    Integer countTotal();
}
