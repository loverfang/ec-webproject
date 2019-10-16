package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.News;
import com.goodcub.vci.entity.NewsExt;
import com.goodcub.vci.vo.admin.NewsInfoVO;
import com.goodcub.vci.vo.admin.SingleNewsVO;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/27
 * @Version V1.0
 **/
public interface NewsService {

    /**
     * 查询单篇类型的文章信息
     * @param ntype
     * @return
     */
    SingleNewsVO querySingleNews(String ntype);

    /**
     * 更新信息基础信息(单表)
     * @param news
     * @return
     */
    Integer updateSingleNews(News news);

    /**
     * 查询新闻列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryNewsList(Map<String,Object> params, int pageNum, int pageSize);

    /**
     * 查询新闻图片列表
     * @param nid
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryNewsImgList(Long nid, int pageNum, int pageSize);

    /**
     * 查询新闻PDF文件列表
     * @param nid
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryNewsPdfList(Long nid, int pageNum, int pageSize);

    /**
     * 保存新闻信息
     * @param news
     * @param newsExt
     */
    void insertNews(News news, NewsExt newsExt);


    /**
     * 根据nid查询信息问信息
     * @param nid
     * @return
     */
    NewsInfoVO queryNewsInfoByNid(Long nid);

    /**
     * 更新新闻信息
     * @param news
     * @param newsExt
     */
    void updateNews(News news, NewsExt newsExt);

    /**
     * 更新新闻表中的基础信息不包含扩展Ext表
     * @param news
     * @return
     */
    Integer updateNews(News news);

    /**
     * 删除新闻信息---包括: 基本信息,扩展信息,图片信息,pdf信息
     * @param idList
     * @return
     */
    Integer deleteNews(List<Long> idList);
}
