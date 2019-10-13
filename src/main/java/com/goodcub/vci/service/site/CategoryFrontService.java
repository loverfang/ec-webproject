package com.goodcub.vci.service.site;

import com.goodcub.vci.vo.site.CategoryFrontVO;
import com.goodcub.vci.vo.site.CategoryListFrontVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryFrontService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/1322:45
 * @Version 1.0
 */
public interface CategoryFrontService {

    /**
     * 前台 -- 查询类别列表信息
     * @param params
     * @return
     */
    List<CategoryListFrontVO> queryCategoryFrontList(Map<String,Object> params);

    /**
     * 前台 -- 查询类别详情信息
     * @param cid
     * @return
     */
    CategoryFrontVO queryCategoryFrontInfo(Integer cid);
}
