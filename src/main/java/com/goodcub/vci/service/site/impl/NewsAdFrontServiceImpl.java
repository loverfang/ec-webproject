package com.goodcub.vci.service.site.impl;

import com.goodcub.vci.mapper.NewsAdMapper;
import com.goodcub.vci.service.site.NewsAdFrontService;
import com.goodcub.vci.vo.site.NewsAdListFrontVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName NewsAdFrontServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/201:39
 * @Version 1.0
 */
@Service
public class NewsAdFrontServiceImpl implements NewsAdFrontService {
    @Resource
    NewsAdMapper newsAdMapper;

    @Override
    public List<NewsAdListFrontVO> queryNewsAdFrontList(Long nid) {
        return newsAdMapper.queryNewsAdFrontList(nid);
    }

}
