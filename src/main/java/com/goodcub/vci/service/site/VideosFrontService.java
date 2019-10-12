package com.goodcub.vci.service.site;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Videos;
import com.goodcub.vci.entity.VideosViewLog;
import com.goodcub.vci.vo.site.VideosListFrontVO;

import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
public interface VideosFrontService {

    TableDataInfo queryFrontVideosList(Map<String, Object> params, int pageNum, int pageSize);

    VideosListFrontVO queryFrontVideoInfo(Integer vid);

    Integer queryViewCountByMemid(Map<String, Object> params);

    Integer insertViewLogs(VideosViewLog videosViewLog);

    Integer updateViewCount(Videos videos);
}
