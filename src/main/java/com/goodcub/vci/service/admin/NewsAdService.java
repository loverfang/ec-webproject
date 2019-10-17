package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.NewsAd;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NewsAdService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/616:27
 * @Version 1.0
 */
public interface NewsAdService {

    /**
     * 通用广告(Ad)文件列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryNewsAdList(Map<String, Object> params, int pageNum, int pageSize);

    /**
     * 保存广告(Ad)文件信息
     * @param newsAd
     * @return
     */
    Integer saveNewsAd(NewsAd newsAd);

    /**
     * 修改广告(Ad)文件信息
     * @param newsAd
     * @return
     */
    Integer updateNewsAd(NewsAd newsAd);

    /**
     * 根据nid和source删除指定新闻下的所有pdf文件
     * @param idList
     * @return
     */
    Integer deleteNewsAd(List<Long> idList);

    /**
     * 根据广告(Ad)的idList直接删除选中的pdf文件信息
     * @param idList
     * @return
     */
    Integer deleteNewsAdByAid(List<Integer> idList);
}
