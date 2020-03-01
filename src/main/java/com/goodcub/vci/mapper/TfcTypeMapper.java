package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.Category;
import com.goodcub.vci.entity.TfcType;
import com.goodcub.vci.vo.admin.TfcTypeOptionsVO;
import com.goodcub.vci.vo.admin.TfcTypeVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TfcTypeMapper
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2020/2/298:30
 * @Version 1.0
 */
public interface TfcTypeMapper {

    /**
     * 查询类别列表信息
     * @param params
     * @return
     */
    List<TfcTypeVO> queryTfcTypeList(Map<String,Object> params);

    /**
     * 添加类别信息
     * @param tfcType
     * @return
     */
    Integer insertTfcType(TfcType tfcType);

    /**
     * 修改类别信息
     * @param tfcType
     * @return
     */
    Integer updateTfcType(TfcType tfcType);

    /**
     * TfcType类型的下拉列表选项
     * @return
     */
    List<TfcTypeOptionsVO> queryTfcTypeOptions();

}
