package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.News;
import com.goodcub.vci.vo.admin.NewsListVO;
import com.goodcub.vci.vo.admin.SingleNewsVO;
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

}
