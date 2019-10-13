package com.goodcub.vci.service.site.impl;

import com.goodcub.vci.mapper.CategoryMapper;
import com.goodcub.vci.service.site.CategoryFrontService;
import com.goodcub.vci.vo.site.CategoryFrontVO;
import com.goodcub.vci.vo.site.CategoryListFrontVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryFrontServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/1322:45
 * @Version 1.0
 */
@Service
public class CategoryFrontServiceImpl implements CategoryFrontService {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryListFrontVO> queryCategoryFrontList(Map<String, Object> params) {
        return categoryMapper.queryCategoryFrontList(params);
    }

    @Override
    public CategoryFrontVO queryCategoryFrontInfo(Integer cid) {
        return categoryMapper.queryCategoryFrontInfo(cid);
    }
}
