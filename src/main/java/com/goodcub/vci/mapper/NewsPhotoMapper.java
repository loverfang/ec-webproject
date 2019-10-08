package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.NewsPhoto;
import com.goodcub.vci.vo.admin.NewsPhotoListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/27
 * @Version V1.0
 **/
public interface NewsPhotoMapper {
    /**
     * 查询单页类型的信息
     * @param params
     * @return
     */
    List<NewsPhotoListVO> queryNewsPhotoList(Map<String, Object> params);

    Integer saveNewsPhoto(NewsPhoto newsPhoto);

    Integer updateNewsPhoto(NewsPhoto newsPhoto);

    Integer deleteNewsPhoto(@Param("idList") List<Integer> idList);

    Integer deleteNewsPhotoByPid(@Param("idList") List<Integer> idList);
}
