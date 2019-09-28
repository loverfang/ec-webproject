package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.News;
import com.goodcub.vci.vo.admin.SingleNewsVO;

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
    Integer updateNews(News news);

    /**
     * 查询新闻列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryNewsList(Map<String,Object> params, int pageNum, int pageSize);

}
