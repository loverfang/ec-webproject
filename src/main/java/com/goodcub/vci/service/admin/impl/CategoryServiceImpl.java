package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Category;
import com.goodcub.vci.mapper.CategoryMapper;
import com.goodcub.vci.service.admin.CategoryService;
import com.goodcub.vci.vo.admin.CategoryOptionsVO;
import com.goodcub.vci.vo.admin.CategoryVO;
import com.goodcub.vci.vo.admin.NewsPhotoVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/516:43
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public TableDataInfo queryCategoryList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,"sindex asc, pubtime desc");
        List<CategoryVO> categoryList = categoryMapper.queryCategoryList(params);

        TableDataInfo TableDataInfo = new TableDataInfo();
        TableDataInfo.setTotal(((List) categoryList).size());
        TableDataInfo.setItems(categoryList);
        return TableDataInfo;
    }

    @Override
    public Integer insertCategory(Category category) {
        return categoryMapper.insertCategory(category);
    }

    @Override
    public Integer updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }

    @Override
    public List<CategoryOptionsVO> queryCategoryOptions() {
        return categoryMapper.queryCategoryOptions();
    }
}
