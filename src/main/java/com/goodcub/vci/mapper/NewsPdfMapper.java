package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.News;
import com.goodcub.vci.entity.NewsExt;
import com.goodcub.vci.entity.NewsPdf;
import com.goodcub.vci.entity.NewsPhoto;
import com.goodcub.vci.vo.admin.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/27
 * @Version V1.0
 **/
public interface NewsPdfMapper {

    /**
     * 查询单页类型的信息
     * @param params
     * @return
     */
    List<NewsPdfVO> queryNewsPdfList(Map<String,Object> params);

    Integer saveNewsPdf(NewsPdf newsPdf);

    Integer updateNewsPdf(NewsPdf newsPdf);

    Integer deleteNewsPdf(Map<String,Object> param);

    Integer deleteNewsPdfByPid(@Param("idList") List<Integer> idList);
}
