package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;

import java.util.Map;

/**
 * @ClassName MemberService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/258:39
 * @Version 1.0
 */
public interface MemberService {

    /**
     * 查询会员信息
     * @param params
     * @return
     */
    TableDataInfo queryMembersList(Map<String,Object> params, int pageNum, int pageSize);

}
