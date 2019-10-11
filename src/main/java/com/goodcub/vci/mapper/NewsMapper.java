package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.News;
import com.goodcub.vci.entity.NewsExt;
import com.goodcub.vci.entity.NewsPdf;
import com.goodcub.vci.entity.NewsPhoto;
import com.goodcub.vci.vo.admin.*;
import com.goodcub.vci.vo.site.ArticleFrontVO;
import com.goodcub.vci.vo.site.NewsListFrontVO;
import com.goodcub.vci.vo.site.PartnerFrontVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/27
 * @Version V1.0
 **/
public interface NewsMapper {

    /**
     * 查询单页类型的信息
     * @param ntype
     * @return
     */
    SingleNewsVO querySingleNews(String ntype);

    /**
     * 更新单页类型的新闻
     * @param news
     * @return
     */
    Integer updateSingleNews(News news);

    /**
     * 根据新闻类型查询不同类型下的新闻列表
     * @param params
     * @return
     */
    List<NewsListVO> queryNewsList(Map<String,Object> params);

    /**
     * 保存新闻基础信息
     * @param news
     * @return
     */
    Integer insertNews(News news);

    /**
     * 保存新闻扩展信息
     * @param newsExt
     * @return
     */
    Integer insertNewsExt(NewsExt newsExt);

    /**
     * 根据新闻ID查询新闻信息
     * @param nid
     * @return
     */
    NewsInfoVO queryNewsInfoByNid(Long nid);

    /**
     * 更新新闻信息
     * @param news
     * @return
     */
    Integer updateNews(News news);

    /**
     * 更新新闻附加信息
     * @param newsExt
     * @return
     */
    Integer updateNewsExt(NewsExt newsExt);

    /**
     * 查询新闻图片列表
     * @param nid
     * @return
     */
    List<NewsPhotoListVO> queryPhotosByNid(Long nid);

    /**
     * 查询新闻Pdf列表
     * @param nid
     * @return
     */
    List<NewsPdfVO> queryPdfByNid(Long nid);

    /**
     * 保存新闻图片信息
     * @param photoList
     * @return
     */
    Integer batchInsertPhotoList(List<NewsPhoto> photoList);

    /**
     * 保存新闻Pdf信息
     * @param photoList
     * @return
     */
    Integer batchInsertPdfList(List<NewsPdf> photoList);


    /**
     * 前台--根据新闻类型查询不同类型下的新闻列表
     * @param params
     * @return
     */
    List<NewsListFrontVO> queryNewsFrontList(Map<String,Object> params);

    /**
     * 前台-- 单篇类型的文章信息
     * @param ntype
     * @return
     */
    ArticleFrontVO queryArticleFrontInfo(String ntype);

    /**
     * 前台-- Partner列表
     * @param params
     * @return
     */
    List<PartnerFrontVO> queryPartnerFrontList(Map<String,Object> params);

}
