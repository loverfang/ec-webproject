package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.NewsAd;
import com.goodcub.vci.mapper.NewsAdMapper;
import com.goodcub.vci.service.admin.NewsAdService;
import com.goodcub.vci.vo.admin.NewsAdListVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NewsPdfServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/616:27
 * @Version 1.0
 */
@Service
public class NewsAdServiceImpl implements NewsAdService {

    @Resource
    NewsAdMapper newsAdMapper;

    @Override
    public TableDataInfo queryNewsAdList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,"sindex asc ,addtime desc");
        List<NewsAdListVO> newsAdList = newsAdMapper.queryNewsAdList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<NewsAdListVO> pageInfo = new PageInfo<>(newsAdList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setTotal(pageInfo.getTotal());
        return tableDataInfo;
    }

    @Override
    public Integer saveNewsAd(NewsAd newsAd) {
        return newsAdMapper.saveNewsAd(newsAd);
    }

    @Override
    public Integer updateNewsAd(NewsAd newsAd) {
        return newsAdMapper.updateNewsAd(newsAd);
    }

    @Override
    public Integer deleteNewsAd(List<Long> idList) {
        return newsAdMapper.deleteNewsAdByNid(idList);
    }

    @Override
    public Integer deleteNewsAdByAid(List<Integer> idList) {
        return newsAdMapper.deleteNewsAdByAid(idList);
    }
}
