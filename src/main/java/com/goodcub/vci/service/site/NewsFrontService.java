package com.goodcub.vci.service.site;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.News;
import com.goodcub.vci.entity.NewsExt;
import com.goodcub.vci.vo.admin.NewsInfoVO;
import com.goodcub.vci.vo.admin.SingleNewsVO;
import com.goodcub.vci.vo.site.ArticleFrontVO;
import com.goodcub.vci.vo.site.PartnerFrontVO;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: 新闻前台查询服务类
 * @Date 2019/9/27
 * @Version V1.0
 **/
public interface NewsFrontService {

    /**
     * 查询单篇类型的文章信息
     * @param ntype
     * @return
     */
    ArticleFrontVO queryArticleInfo(String ntype);

    /**
     * 查询合作伙伴列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryPartenerFrontList(Map<String, Object> params, int pageNum, int pageSize);

    /**
     * 查询新闻列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryNewsFrontList(Map<String, Object> params, int pageNum, int pageSize);

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
     * 根据nid查询信息问信息
     * @param nid
     * @return
     */
    NewsInfoVO queryNewsInfoByNid(Long nid);

}
