package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.TfcVideos;
import com.goodcub.vci.entity.Videos;
import com.goodcub.vci.mapper.TfcVideosMapper;
import com.goodcub.vci.mapper.VideosMapper;
import com.goodcub.vci.service.admin.TfcVideosService;
import com.goodcub.vci.service.admin.VideosService;
import com.goodcub.vci.vo.admin.TfcVideosVO;
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
public class TfcVideosServiceImpl implements TfcVideosService {

    @Resource
    TfcVideosMapper tfcVideosMapper;

    @Override
    public TableDataInfo queryTfcVideosList(Map<String,Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"sindex asc, addtime desc");
        List<TfcVideosVO> tfcVideosListVOList = tfcVideosMapper.queryTfcVideosList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<TfcVideosVO> pageInfo = new PageInfo<>(tfcVideosListVOList, pageSize);
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setTotal(pageInfo.getTotal());

        return tableDataInfo;
    }

    @Override
    public Integer countTotal() {
        return tfcVideosMapper.countTotal();
    }

    @Override
    public Integer saveTfcVideos(TfcVideos tfcVideos) {
        return tfcVideosMapper.insertTfcVideos(tfcVideos);
    }

    @Override
    public Integer updateTfcVideos(TfcVideos tfcVideos) {
        return tfcVideosMapper.updateTfcVideos(tfcVideos);
    }

    @Override
    @Transactional
    public Integer deleteTfcVideos(List<Integer> idList) {
        return tfcVideosMapper.deleteTfcVideos(idList);
    }

}
