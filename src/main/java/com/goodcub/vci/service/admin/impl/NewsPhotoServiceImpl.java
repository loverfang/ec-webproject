package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.NewsPhoto;
import com.goodcub.vci.mapper.NewsPhotoMapper;
import com.goodcub.vci.service.admin.NewsPhotoService;
import com.goodcub.vci.vo.admin.NewsPhotoListVO;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
//
    @Resource
    SqlSessionFactory sqlSessionFactory;

    @Override
    public TableDataInfo queryNewsPhotoList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"sindex asc ,uptime desc");
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

    @Transactional
    @Override
    public Integer saveBatchNewsPhoto(List<NewsPhoto> newsPhotoList) {

        Integer resultCount = 0;
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        // 批量保存执行前时间
        long start=System.currentTimeMillis();

        try{
            NewsPhotoMapper newsPhotoMmapper = sqlSession.getMapper(NewsPhotoMapper.class);
            List<NewsPhoto> batchNewsPhotoList = new ArrayList<NewsPhoto>();
            for (int i = 0; i < newsPhotoList.size(); i++) {
                NewsPhoto newsPhoto = newsPhotoList.get(i);
                batchNewsPhotoList.add(newsPhoto);
                if(i%500==0){
                    resultCount += newsPhotoMmapper.saveNewsPhotoBatch(batchNewsPhotoList);
                    batchNewsPhotoList.clear();
                }
            }
            if(batchNewsPhotoList!=null && batchNewsPhotoList.size()>0) {
                resultCount += newsPhotoMmapper.saveNewsPhotoBatch(batchNewsPhotoList);
            }
        }finally{
            sqlSession.commit();
            sqlSession.close();
        }

        // 批量保存执行完成时间
        long end =System.currentTimeMillis();
        System.out.println("耗时[" + ( end - start )+"]插入" + resultCount + "条");
        return null;
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
