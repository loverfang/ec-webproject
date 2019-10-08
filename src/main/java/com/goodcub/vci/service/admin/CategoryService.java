package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Category;
import com.goodcub.vci.vo.admin.CategoryOptionsVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/516:42
 * @Version 1.0
 */
public interface CategoryService {
    /**
     * 查询Category类别列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryCategoryList(Map<String,Object> params, int pageNum, int pageSize);

    /**
     * 添加类别信息
     * @param category
     * @return
     */
    Integer insertCategory(Category category);

    /**
     * 修改类别信息
     * @param category
     * @return
     */
    Integer updateCategory(Category category);

    /**
     * Vendor类型的下拉列表信息
     * @return
     */
    List<CategoryOptionsVO> queryCategoryOptions();
}
