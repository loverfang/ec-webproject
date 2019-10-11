package com.goodcub.vci.service.site.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.enums.RecomendEnum;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.vci.entity.News;
import com.goodcub.vci.entity.NewsExt;
import com.goodcub.vci.mapper.NewsMapper;
import com.goodcub.vci.service.admin.NewsService;
import com.goodcub.vci.service.site.NewsFrontService;
import com.goodcub.vci.vo.admin.*;
import com.goodcub.vci.vo.site.ArticleFrontVO;
import com.goodcub.vci.vo.site.NewsListFrontVO;
import com.goodcub.vci.vo.site.PartnerFrontVO;
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
public class NewsFrontServiceImpl implements NewsFrontService {

    @Resource
    NewsMapper newsMapper;

    @Override
    public ArticleFrontVO queryArticleInfo(String ntype) {
        return newsMapper.queryArticleFrontInfo(ntype);
    }

    @Override
    public TableDataInfo queryPartenerFrontList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"ifindex desc, sindex asc, pubtime desc");

        List<PartnerFrontVO> newsList = newsMapper.queryPartnerFrontList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<PartnerFrontVO> pageInfo = new PageInfo<>(newsList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(pageInfo.getTotal());
        tableDataInfo.setItems(pageInfo.getList());
        return tableDataInfo;
    }

    @Override
    public TableDataInfo queryNewsFrontList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"ifindex desc, sindex asc, pubtime desc");
        List<NewsListFrontVO> newsList = newsMapper.queryNewsFrontList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<NewsListFrontVO> pageInfo = new PageInfo<>(newsList, pageSize);

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

}
