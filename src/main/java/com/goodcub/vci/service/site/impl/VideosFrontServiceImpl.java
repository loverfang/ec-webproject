package com.goodcub.vci.service.site.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Videos;
import com.goodcub.vci.entity.VideosViewLog;
import com.goodcub.vci.mapper.VideosMapper;
import com.goodcub.vci.service.site.VideosFrontService;
import com.goodcub.vci.vo.site.VideosListFrontVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
@Service
public class VideosFrontServiceImpl implements VideosFrontService {

    @Resource
    VideosMapper videosMapper;

    @Override
    public TableDataInfo queryFrontVideosList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize," sindex asc, addtime desc");

        List<VideosListFrontVO> newsList = videosMapper.queryFrontVideosList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<VideosListFrontVO> pageInfo = new PageInfo<>(newsList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(pageInfo.getTotal());
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setPageNum(pageInfo.getPageNum());
        tableDataInfo.setPageSize(pageInfo.getPageSize());
        tableDataInfo.setPages(pageInfo.getPages());
        return tableDataInfo;
    }

    @Override
    public VideosListFrontVO queryFrontVideoInfo(Integer vid) {
        return videosMapper.queryFrontVideoInfo(vid);
    }

    @Override
    public Integer queryViewCountByMemid(Map<String, Object> params) {
        return videosMapper.queryViewCountByMemid(params);
    }

    @Override
    public Integer insertViewLogs(VideosViewLog videosViewLog) {
        return videosMapper.insertViewLogs(videosViewLog);
    }

    @Override
    public Integer updateViewCount(Videos videos) {
        return videosMapper.updateViewCount(videos);
    }
}
