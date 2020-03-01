package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Category;
import com.goodcub.vci.entity.TfcType;
import com.goodcub.vci.mapper.CategoryMapper;
import com.goodcub.vci.mapper.TfcTypeMapper;
import com.goodcub.vci.service.admin.CategoryService;
import com.goodcub.vci.service.admin.TfcTypeService;
import com.goodcub.vci.vo.admin.CategoryOptionsVO;
import com.goodcub.vci.vo.admin.CategoryVO;
import com.goodcub.vci.vo.admin.TfcTypeOptionsVO;
import com.goodcub.vci.vo.admin.TfcTypeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TfcTyServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/516:43
 * @Version 1.0
 */
@Service
public class TfcTypeServiceImpl implements TfcTypeService {

    @Resource
    TfcTypeMapper tfcTypeMapper;

    @Override
    public TableDataInfo queryTfcTypeList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,"sindex asc, add_time desc");
        List<TfcTypeVO> categoryList = tfcTypeMapper.queryTfcTypeList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<TfcTypeVO> pageInfo = new PageInfo<>(categoryList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(pageInfo.getTotal());
        tableDataInfo.setItems(pageInfo.getList());
        return tableDataInfo;
    }

    @Override
    public Integer insertTfcType(TfcType tfcType) {
        return tfcTypeMapper.insertTfcType(tfcType);
    }

    @Override
    public Integer updateTfcType(TfcType tfcType) {
        return tfcTypeMapper.updateTfcType(tfcType);
    }

    @Override
    public List<TfcTypeOptionsVO> queryTfcTypeOptions() {
        return tfcTypeMapper.queryTfcTypeOptions();
    }
}
