package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Category;
import com.goodcub.vci.mapper.CategoryMapper;
import com.goodcub.vci.service.admin.CategoryService;
import com.goodcub.vci.vo.admin.CategoryOptionsVO;
import com.goodcub.vci.vo.admin.CategoryVO;
import com.goodcub.vci.vo.admin.NewsPhotoListVO;
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

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<CategoryVO> pageInfo = new PageInfo<>(categoryList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(pageInfo.getTotal());
        tableDataInfo.setItems(pageInfo.getList());
        return tableDataInfo;
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
