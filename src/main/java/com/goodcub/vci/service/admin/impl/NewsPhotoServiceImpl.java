package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.NewsPhoto;
import com.goodcub.vci.mapper.NewsPhotoMapper;
import com.goodcub.vci.service.admin.NewsPhotoService;
import com.goodcub.vci.vo.admin.NewsPhotoListVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NewsPhotoServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/616:27
 * @Version 1.0
 */
@Service
public class NewsPhotoServiceImpl implements NewsPhotoService {

    @Resource
    NewsPhotoMapper newsPhotoMapper;

    @Override
    public TableDataInfo queryNewsPhotoList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,"sindex asc ,uptime desc");
        List<NewsPhotoListVO> newsPhotoList = newsPhotoMapper.queryNewsPhotoList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<NewsPhotoListVO> pageInfo = new PageInfo<>(newsPhotoList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(pageInfo.getTotal());
        tableDataInfo.setItems(pageInfo.getList());
        return tableDataInfo;
    }

    @Override
    public Integer saveNewsPhoto(NewsPhoto newsPhoto) {
        return newsPhotoMapper.saveNewsPhoto(newsPhoto);
    }

    @Override
    public Integer updateNewsPhoto(NewsPhoto newsPhoto) {
        return newsPhotoMapper.updateNewsPhoto(newsPhoto);
    }

    @Override
    public Integer deleteNewsPhoto(List<Integer> idList) {
        return newsPhotoMapper.deleteNewsPhoto(idList);
    }

    @Override
    public Integer deleteNewsPhotoByPid(List<Integer> idList) {
        return newsPhotoMapper.deleteNewsPhotoByPid(idList);
    }
}
