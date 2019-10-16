package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.NewsPdf;
import com.goodcub.vci.entity.NewsPhoto;

import java.util.List;
import java.util.Map;

/**
 * @ClassName NewsPdfService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/616:27
 * @Version 1.0
 */
public interface NewsPhotoService {

    /**
     * 通用Photo文件列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryNewsPhotoList(Map<String, Object> params, int pageNum, int pageSize);

    /**
     * 保存Photo文件信息
     * @param newsPhoto
     * @return
     */
    Integer saveNewsPhoto(NewsPhoto newsPhoto);

    /**
     * 批量保存Photo信息
     * @param newsPhotoList
     * @return
     */
    Integer saveBatchNewsPhoto(List<NewsPhoto> newsPhotoList);

    /**
     * 修改Photo文件信息
     * @param newsPhoto
     * @return
     */
    Integer updateNewsPhoto(NewsPhoto newsPhoto);

    /**
     * 根据nid和source删除指定新闻下的所有Photo文件
     * @param idList
     * @return
     */
    Integer deleteNewsPhoto(List<Long> idList);

    /**
     * 根据Photo的idList直接删除选中的Photo文件信息
     * @param idList
     * @return
     */
    Integer deleteNewsPhotoByPid(List<Integer> idList);
}
