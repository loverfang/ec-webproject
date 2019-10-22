package com.goodcub.vci.service.site;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.vo.site.NewsPdfFrontVO;

import java.util.Map;

/**
 * @ClassName NewsPdfFrontService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/1323:51
 * @Version 1.0
 */
public interface NewsPdfFrontService {

    TableDataInfo queryNewsPdfFrontList(Map<String, Object> params, int pageNum, int pageSize);

    NewsPdfFrontVO queryNewsPdfFrontInfo(Integer pid);
}
