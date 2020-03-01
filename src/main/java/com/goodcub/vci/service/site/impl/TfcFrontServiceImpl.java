package com.goodcub.vci.service.site.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.mapper.TfcVideosMapper;
import com.goodcub.vci.service.site.TfcFrontService;
import com.goodcub.vci.vo.site.AdvertiseFrontVO;
import com.goodcub.vci.vo.site.TfcTypeFrontVO;
import com.goodcub.vci.vo.site.TfcVideosFrontVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TfcFrontServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2020/3/116:26
 * @Version 1.0
 */
@Service
public class TfcFrontServiceImpl implements TfcFrontService {

    @Resource
    TfcVideosMapper tfcVideosMapper;

    @Override
    public List<TfcTypeFrontVO> queryTfcTypeList() {
        return tfcVideosMapper.queryTfcTypeList();
    }

    @Override
    public TableDataInfo queryTfcFrontVOList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"v.sindex asc, v.addtime desc");
        List<TfcVideosFrontVO> advertiseVOList = tfcVideosMapper.queryFrontTfcVideosList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<TfcVideosFrontVO> pageInfo = new PageInfo<>(advertiseVOList, pageSize);
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setTotal(pageInfo.getTotal());
        tableDataInfo.setPageNum(pageInfo.getPageNum());
        tableDataInfo.setPageSize(pageInfo.getPageSize());
        tableDataInfo.setPages(pageInfo.getPages());

        return tableDataInfo;
    }

}
