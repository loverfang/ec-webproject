package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.Members;
import com.goodcub.vci.mapper.MemberMapper;
import com.goodcub.vci.mapper.NewsMapper;
import com.goodcub.vci.mapper.VideosMapper;
import com.goodcub.vci.service.admin.MainService;
import com.goodcub.vci.service.admin.MemberService;
import com.goodcub.vci.vo.admin.NewsListVO;
import com.goodcub.vci.vo.admin.VideosListVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/17
 * @Version V1.0
 **/
@Service
public class MainServiceImpl implements MainService {

    @Resource
    NewsMapper newsMapper;
    @Resource
    VideosMapper videosMapper;
    @Resource
    MemberMapper memberMapper;

    @Override
    public TableDataInfo queryIndexNewsList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"pubtime desc");
        List<NewsListVO> newsList = newsMapper.queryNewsAdminIndexList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<NewsListVO> pageInfo = new PageInfo<>(newsList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(pageInfo.getTotal());
        tableDataInfo.setItems(pageInfo.getList());
        return tableDataInfo;
    }

    @Override
    public TableDataInfo queryIndexVideosList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize," addtime desc");
        List<VideosListVO> videosListVOList = videosMapper.queryVideosIndexList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<VideosListVO> pageInfo = new PageInfo<>(videosListVOList, pageSize);
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setTotal(pageInfo.getTotal());

        return tableDataInfo;
    }

    @Override
    public TableDataInfo queryIndexMembersList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"regtime desc");
        List<Members> membersList = memberMapper.queryMembersIndexList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Members> pageInfo = new PageInfo<>(membersList, pageSize);
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setTotal(pageInfo.getTotal());
        return tableDataInfo;
    }
}
