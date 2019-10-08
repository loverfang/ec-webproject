package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.Videos;
import com.goodcub.vci.vo.admin.AdvertiseVO;
import com.goodcub.vci.vo.admin.VideosListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
public interface VideosMapper {

    /**
     * 查询视频列表
     * @param params
     * @return
     */
    List<VideosListVO> queryVideosList(Map<String, Object> params);

    Integer insertVideos(Videos videos);

    Integer updateVideos(Videos videos);

    Integer deleteVideos(@Param("idList") List<Integer> idList);

}
