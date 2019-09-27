package com.goodcub.vci.service.admin.impl;

import com.goodcub.vci.entity.News;
import com.goodcub.vci.mapper.NewsMapper;
import com.goodcub.vci.service.admin.NewsService;
import com.goodcub.vci.vo.admin.SingleNewsVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

}
