package com.goodcub.vci.service.site.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.mapper.NewsPhotoMapper;
import com.goodcub.vci.service.site.NewsPhotoFrontService;
import com.goodcub.vci.vo.site.NewsPdfListFrontVO;
import com.goodcub.vci.vo.site.NewsPhotoListFrontVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/15
 * @Version V1.0
 **/
@Service
public class NewsPhotoFrontServiceImpl implements NewsPhotoFrontService {

    @Resource
    NewsPhotoMapper newsPhotoMapper;

    @Override
    public List<NewsPhotoListFrontVO> queryNewsPhotoFrontList(Long nid) {
        return newsPhotoMapper.queryNewsPhotoFrontList(nid);
    }

}
