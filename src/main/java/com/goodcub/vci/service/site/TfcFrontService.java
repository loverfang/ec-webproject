package com.goodcub.vci.service.site;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.vo.site.TfcTypeFrontVO;
import com.goodcub.vci.vo.site.TfcVideosFrontVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TfcFrontService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2020/3/111:27
 * @Version 1.0
 */
public interface TfcFrontService {

    List<TfcTypeFrontVO> queryTfcTypeList();

    TableDataInfo queryTfcFrontVOList(Map<String, Object> params, int pageNum, int pageSize);
    
}
