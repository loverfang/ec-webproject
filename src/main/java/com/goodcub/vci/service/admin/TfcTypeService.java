package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.TfcType;
import com.goodcub.vci.vo.admin.TfcTypeOptionsVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TfcTypeService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/516:42
 * @Version 1.0
 */
public interface TfcTypeService {

    /**
     * 查询TfcType类别列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryTfcTypeList(Map<String, Object> params, int pageNum, int pageSize);

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
     * Vendor类型的下拉列表信息
     * @return
     */
    List<TfcTypeOptionsVO> queryTfcTypeOptions();
}
