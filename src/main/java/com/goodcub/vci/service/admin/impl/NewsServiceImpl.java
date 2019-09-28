package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.News;
import com.goodcub.vci.mapper.NewsMapper;
import com.goodcub.vci.service.admin.NewsService;
import com.goodcub.vci.vo.admin.NewsListVO;
import com.goodcub.vci.vo.admin.SingleNewsVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/27
 * @Version V1.0
 **/
@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    NewsMapper newsMapper;

    @Override
    public SingleNewsVO querySingleNews(String ntype) {
        return newsMapper.querySingleNews(ntype);
    }

    @Override
    public Integer updateNews(News news) {
        return newsMapper.updateSingleNews(news);
    }

    @Override
    public TableDataInfo queryNewsList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<NewsListVO> newsList = newsMapper.queryNewsList(params);

        TableDataInfo TableDataInfo = new TableDataInfo();
        TableDataInfo.setTotal(((List) newsList).size());
        TableDataInfo.setItems(newsList);
        return TableDataInfo;
    }
}
