package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.enums.RecomendEnum;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.vci.entity.Members;
import com.goodcub.vci.entity.News;
import com.goodcub.vci.entity.NewsExt;
import com.goodcub.vci.mapper.NewsAdMapper;
import com.goodcub.vci.mapper.NewsMapper;
import com.goodcub.vci.mapper.NewsPdfMapper;
import com.goodcub.vci.mapper.NewsPhotoMapper;
import com.goodcub.vci.service.admin.NewsService;
import com.goodcub.vci.vo.admin.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
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

    private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);
    @Resource
    NewsMapper newsMapper;

    @Resource
    NewsPhotoMapper newsPhotoMapper;

    @Resource
    NewsPdfMapper newsPdfMapper;

    @Resource
    NewsAdMapper newsAdMapper;

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

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(pageInfo.getTotal());
        tableDataInfo.setItems(pageInfo.getList());
        return tableDataInfo;
    }

    @Override
    public TableDataInfo queryNewsImgList(Long nid, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,"sindex asc");
        List<NewsPhotoListVO> newsImgList = newsMapper.queryPhotosByNid(nid);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<NewsPhotoListVO> pageInfo = new PageInfo<>(newsImgList, pageSize);

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
        news.setIfindex(RecomendEnum.N);
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
    public Integer deleteNews(List<Long> idList) {
        Integer base_count = 0;
        Integer ext_count = 0;
        Integer photo_count = 0;
        Integer pdf_count = 0;
        Integer ad_count = 0;

        // 1新闻基本信息
        base_count = newsMapper.deleteNewsByNids(idList);
        // 2新闻扩展信息
        ext_count = newsMapper.deleteNewsExtByNids(idList);
        // 3新闻图片信息
        photo_count = newsPhotoMapper.deleteNewsPhotoByNid(idList);
        // 4新闻PDF信息
        Map<String,Object> param = new HashMap<>();
        param.put("source","news");
        param.put("idList",idList);
        pdf_count = newsPdfMapper.deleteNewsPdfByNid(param);

        // 5新闻广告信息
        ad_count = newsAdMapper.deleteNewsAdByNid(idList);
        logger.info("成功删除,基本信息{}条,扩展{}条,图片{}条,PDF{}条,广告{}条!", base_count, ext_count, photo_count, pdf_count, ad_count);
        return base_count;
    }
}
