package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.enums.RecomendEnum;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.vci.entity.Members;
import com.goodcub.vci.entity.News;
import com.goodcub.vci.entity.NewsExt;
import com.goodcub.vci.mapper.NewsMapper;
import com.goodcub.vci.service.admin.NewsService;
import com.goodcub.vci.vo.admin.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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
    public Integer updateSingleNews(News news) {
        return newsMapper.updateSingleNews(news);
    }

    @Override
    public TableDataInfo queryNewsList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"ifindex desc, sindex asc, pubtime desc");
        List<NewsListVO> newsList = newsMapper.queryNewsList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<NewsListVO> pageInfo = new PageInfo<>(newsList, pageSize);

        TableDataInfo TableDataInfo = new TableDataInfo();
        TableDataInfo.setTotal(pageInfo.getTotal());
        TableDataInfo.setItems(pageInfo.getList());
        return TableDataInfo;
    }

    @Override
    public TableDataInfo queryNewsImgList(Long nid, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,"sindex asc");
        List<NewsPhotoListVO> newsImgList = newsMapper.queryPhotosByNid(nid);

        TableDataInfo TableDataInfo = new TableDataInfo();
        TableDataInfo.setTotal(((List) newsImgList).size());
        TableDataInfo.setItems(newsImgList);
        return TableDataInfo;
    }

    @Override
    public TableDataInfo queryNewsPdfList(Long nid, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,"sindex asc");
        List<NewsPdfVO> newsPdfList = newsMapper.queryPdfByNid(nid);

        TableDataInfo TableDataInfo = new TableDataInfo();
        TableDataInfo.setTotal(((List) newsPdfList).size());
        TableDataInfo.setItems(newsPdfList);
        return TableDataInfo;
    }

    @Override
    public NewsInfoVO queryNewsInfoByNid(Long nid) {
        return newsMapper.queryNewsInfoByNid(nid);
    }

    @Transactional
    @Override
    public void insertNews(News news, NewsExt newsExt) {
        news.setPubtime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()));
        // 默认排序索引
        news.setSindex(1);
        // 默认不推荐/置顶
        news.setIfindex(RecomendEnum.FAILER.getCode());
        news.setViewcount(0);
        newsMapper.insertNews(news);

        // 测试事务问题
        newsMapper.insertNewsExt(newsExt);
    }

    public void updateNews(News news, NewsExt newsExt) {
        newsMapper.updateNews(news);

        // 测试事务问题
        newsMapper.updateNewsExt(newsExt);
    }

    @Override
    public Integer updateNews(News news) {
        return newsMapper.updateNews(news);
    }

    @Override
    @Transactional
    public Integer deleteNews(List<Integer> idList) {

        // 1新闻基本信息

        // 2新闻扩展信息

        // 3新闻图片信息

        // 4新闻PDF信息

        // 5新闻广告信息

        return null;
    }
}
