package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;

import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/17
 * @Version V1.0
 **/
public interface MainService {

    /**
     * 首页新闻列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryIndexNewsList(Map<String,Object> params, int pageNum, int pageSize);

    /**
     * 首页视频列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryIndexVideosList(Map<String, Object> params, int pageNum, int pageSize);

    /**
     * 首页会员列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    TableDataInfo queryIndexMembersList(Map<String,Object> params, int pageNum, int pageSize);

}
