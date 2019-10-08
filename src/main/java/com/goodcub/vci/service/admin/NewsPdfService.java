package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.NewsPdf;

import java.util.List;
import java.util.Map;

/**
 * @ClassName NewsPdfService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/616:27
 * @Version 1.0
 */
public interface NewsPdfService {

    /**
     * 通用PDF文件列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryNewsPdfList(Map<String,Object> params, int pageNum, int pageSize);

    /**
     * 保存PDF文件信息
     * @param newsPdf
     * @return
     */
    Integer saveNewsPdf(NewsPdf newsPdf);

    /**
     * 修改PDF文件信息
     * @param newsPdf
     * @return
     */
    Integer updateNewsPdf(NewsPdf newsPdf);

    /**
     * 根据nid和source删除指定新闻下的所有pdf文件
     * @param param
     * @return
     */
    Integer deleteNewsPdf(Map<String,Object> param);

    /**
     * 根据pdf的idList直接删除选中的pdf文件信息
     * @param idList
     * @return
     */
    Integer deleteNewsPdfByPid(List<Integer> idList);
}
