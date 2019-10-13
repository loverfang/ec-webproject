package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.Category;
import com.goodcub.vci.vo.admin.CategoryOptionsVO;
import com.goodcub.vci.vo.admin.CategoryVO;
import com.goodcub.vci.vo.site.CategoryFrontVO;
import com.goodcub.vci.vo.site.CategoryListFrontVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName Category
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/516:34
 * @Version 1.0
 */
public interface CategoryMapper {

    /**
     * 查询类别列表信息
     * @param params
     * @return
     */
    List<CategoryVO> queryCategoryList(Map<String,Object> params);

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
     * Vendor类型的下拉列表选项
     * @return
     */
    List<CategoryOptionsVO> queryCategoryOptions();

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
