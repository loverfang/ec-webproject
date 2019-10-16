package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.NewsAd;
import com.goodcub.vci.entity.NewsPdf;
import com.goodcub.vci.vo.admin.NewsAdListVO;
import com.goodcub.vci.vo.admin.NewsPdfVO;
import com.goodcub.vci.vo.site.NewsAdListFrontVO;
import com.goodcub.vci.vo.site.NewsPdfListFrontVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/27
 * @Version V1.0
 **/
public interface NewsAdMapper {

    /**
     * 新闻下边的广告信息
     * @param params
     * @return
     */
    List<NewsAdListVO> queryNewsAdList(Map<String, Object> params);

    Integer saveNewsAd(NewsAd newsPdf);

    Integer updateNewsAd(NewsAd newsPdf);

    Integer deleteNewsAdByNid(@Param("idList") List<Long> idList);

    Integer deleteNewsAdById(@Param("idList") List<Integer> idList);

    /**
     * 前台--查询新闻列表下的广告信息
     * @param nid
     * @return
     */
    List<NewsAdListFrontVO> queryNewsAdFrontList(Long nid);
}
