package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.News;
import com.goodcub.vci.vo.admin.SingleNewsVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/27
 * @Version V1.0
 **/
public interface NewsMapper {

    SingleNewsVO querySingleNews(String ntype);

    Integer updateSingleNews(News news);

}
