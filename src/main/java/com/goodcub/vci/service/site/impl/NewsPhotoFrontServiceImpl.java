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
    public TableDataInfo queryNewsPhotoFrontList(Long nid, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize,"sindex desc ,uptime desc");
        List<NewsPhotoListFrontVO> photoList = newsPhotoMapper.queryNewsPhotoFrontList(nid);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<NewsPhotoListFrontVO> pageInfo = new PageInfo<>(photoList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(pageInfo.getTotal());
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setPageNum(pageInfo.getPageNum());
        tableDataInfo.setPageSize(pageInfo.getPageSize());
        tableDataInfo.setPages(pageInfo.getPages());

        return tableDataInfo;
    }

}
