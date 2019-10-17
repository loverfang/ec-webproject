package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Advertise;
import com.goodcub.vci.entity.Videos;
import com.goodcub.vci.mapper.AdvertiseMapper;
import com.goodcub.vci.mapper.VideosMapper;
import com.goodcub.vci.service.admin.AdvertiseService;
import com.goodcub.vci.service.admin.VideosService;
import com.goodcub.vci.vo.admin.AdvertiseVO;
import com.goodcub.vci.vo.admin.VideosListVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
@Service
public class VideosServiceImpl implements VideosService {

    @Resource
    VideosMapper videosMapper;

    @Override
    public TableDataInfo queryVideosList(Map<String,Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"sindex asc, addtime desc");
        List<VideosListVO> videosListVOList = videosMapper.queryVideosList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<VideosListVO> pageInfo = new PageInfo<>(videosListVOList, pageSize);
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setTotal(pageInfo.getTotal());

        return tableDataInfo;
    }


    @Override
    public Integer countTotal() {
        return videosMapper.countTotal();
    }

    @Override
    public Integer saveVideos(Videos videos) {
        return videosMapper.insertVideos(videos);
    }

    @Override
    public Integer updateVideos(Videos videos) {
        return videosMapper.updateVideos(videos);
    }

    @Override
    @Transactional
    public Integer deleteVideos(List<Integer> idList) {
        return videosMapper.deleteVideos(idList);
    }
}
