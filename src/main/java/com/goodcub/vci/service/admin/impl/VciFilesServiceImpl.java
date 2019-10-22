package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.VciFiles;
import com.goodcub.vci.mapper.VciFilesMapper;
import com.goodcub.vci.service.admin.VciFilesService;
import com.goodcub.vci.vo.admin.VciFileListVO;

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
public class VciFilesServiceImpl implements VciFilesService {

    @Resource
    VciFilesMapper vciFilesMapper;

    @Resource
    SqlSessionFactory sqlSessionFactory;

    @Override
    public TableDataInfo queryVciFilesList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"uptime desc");
        List<VciFileListVO> vciFileList = vciFilesMapper.queryVciFilesList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<VciFileListVO> pageInfo = new PageInfo<>(vciFileList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(pageInfo.getTotal());
        tableDataInfo.setItems(pageInfo.getList());
        return tableDataInfo;
    }

    @Transactional
    @Override
    public Integer saveBatchVciFiles(List<VciFiles> vciFilesList) {

        Integer resultCount = 0;
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        // 批量保存执行前时间
        long start=System.currentTimeMillis();

        try{
            VciFilesMapper tempVciFilesMapper = sqlSession.getMapper(VciFilesMapper.class);
            List<VciFiles> batchVciFilesList = new ArrayList<VciFiles>();
            for (int i = 0; i < vciFilesList.size(); i++) {
                VciFiles vciFiles = vciFilesList.get(i);
                batchVciFilesList.add(vciFiles);
                if(i%500==0){
                    resultCount += tempVciFilesMapper.saveVciFilesBatch(batchVciFilesList);
                    batchVciFilesList.clear();
                }
            }
            if(batchVciFilesList!=null && batchVciFilesList.size()>0) {
                resultCount += tempVciFilesMapper.saveVciFilesBatch(batchVciFilesList);
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
    public Integer deleteVciFilesByFid(List<Long> idList) {
        return vciFilesMapper.deleteVciFilesById( idList );
    }
}
