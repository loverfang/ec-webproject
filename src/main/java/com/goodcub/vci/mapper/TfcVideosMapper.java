package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.TfcVideos;
import com.goodcub.vci.vo.admin.TfcVideosVO;
import com.goodcub.vci.vo.site.TfcTypeFrontVO;
import com.goodcub.vci.vo.site.TfcVideosFrontVO;
import com.goodcub.vci.vo.site.VideosListFrontVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
public interface TfcVideosMapper {

    /**
     * 查询视频列表
     * @param params
     * @return
     */
    List<TfcVideosVO> queryTfcVideosList(Map<String, Object> params);

    /**
     * 查询后台首页视频列表
     * @param params
     * @return
     */
    List<TfcVideosVO> queryTfcVideosIndexList(Map<String, Object> params);

    Integer insertTfcVideos(TfcVideos tfcVideos);

    Integer updateTfcVideos(TfcVideos tfcVideos);

    Integer deleteTfcVideos(@Param("idList") List<Integer> idList);

    Integer countTotal();

    List<TfcVideosFrontVO> queryFrontTfcVideosList(Map<String, Object> params);

    VideosListFrontVO queryFrontTfcVideoInfo(Integer vid);

    Integer queryViewCountByMemid(Map<String, Object> params);

    // Integer insertViewLogs(VideosViewLog videosViewLog);

    Integer updateViewCount(TfcVideos tfcVideos);

    List<TfcTypeFrontVO> queryTfcTypeList();
}
